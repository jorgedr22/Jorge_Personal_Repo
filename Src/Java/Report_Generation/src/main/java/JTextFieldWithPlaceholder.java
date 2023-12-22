package main.java;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * A JTextField with a placeholder that is displayed when the text field is empty and not in focus.
 * @author Report Engine Team
 */
public class JTextFieldWithPlaceholder extends JTextField {

    private String placeholder;
    private boolean placeholderActive;

    /**
     * Constructs a JTextFieldWithPlaceholder with the specified placeholder text.
     *
     * @param placeholder The placeholder text to be displayed.
     */
    public JTextFieldWithPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        this.placeholderActive = true;

        // Set the initial placeholder
        setPlaceholder();

        // Add a focus listener to handle placeholder behavior
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (placeholderActive) {
                    setForeground(Color.BLACK);
                    setText("");
                    placeholderActive = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setPlaceholder();
                }
            }
        });
    }

    /**
     * Sets the placeholder text and color.
     */
    private void setPlaceholder() {
        setForeground(Color.GRAY);
        setText(placeholder);
        placeholderActive = true;
    }
}
