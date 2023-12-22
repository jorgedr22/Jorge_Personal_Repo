package main.java;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static javax.mail.Message.RecipientType.TO;

/**
 * GMailer is a class for sending emails using the Gmail API.
 * An example is as follows: new GMailer.sendMail("Your Subject", "Your
 * message", your_file, "TheEmailTo@website.com")
 * @author Report Engine Team
 */
public class GMailer {

    // From address
    private static final String TEST_FROM = "sfwereportgenerator@gmail.com";

    // Instance of Gmail API
    private final Gmail service;

    /**
     * Constructor for GMailer.
     *
     * @throws Exception if an error occurs during initialization
     */
    public GMailer() throws Exception {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("Test Mailer")
                .build();
    }

    /*
     * Retrieves Gmail API credentials.
     *
     * @param httpTransport the HTTP transport
     * 
     * @param jsonFactory the JSON factory
     * 
     * @return the credentials
     * 
     * @throws IOException if an error occurs during credential retrieval
     */
    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
                new InputStreamReader(GMailer.class.getResourceAsStream("/credentials.json")));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Sends an email with an attachment.
     *
     * @param subject the email subject
     * @param message the email message
     * @param file    the file to be attached
     * @throws Exception if an error occurs during email sending
     */
    public void sendMail(String subject, String message, File file, String to) throws Exception {
        final String toString = to;
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(TEST_FROM));
        email.addRecipient(TO, new InternetAddress(toString));
        email.setSubject(subject);
        email.setText(message);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(message, "text/plain");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        mimeBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(file);
        mimeBodyPart.setDataHandler(new DataHandler(source));
        mimeBodyPart.setFileName(file.getName());
        multipart.addBodyPart(mimeBodyPart);
        email.setContent(multipart);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);

        //System.out.println("Attempting Sending Email");
        try {
            msg = service.users().messages().send("me", msg).execute();
            //System.out.println("Message id: " + msg.getId());
            //System.out.println(msg.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.out.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }
    }

}