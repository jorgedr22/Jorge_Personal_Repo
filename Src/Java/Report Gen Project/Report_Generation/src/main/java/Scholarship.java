package main.java;

/**
 * This class represents a Scholarship with various attributes such as name, payout, deadline, etc.
 * The author of this class is backend.
 * @author Backend Team
 */
public class Scholarship {
    private String scholarshipName;
    private int payout;
    private String deadline;
    private String disbursmentDate;
    private String customRequiredInfo;
    private String preferedMajors;
    private String donorContact;
    private String eligible;

    /**
     * Default constructor for the Scholarship class.
     * Initializes default values for various attributes.
     */
    public Scholarship() { // This is the constructor
        this.scholarshipName = "No Name";
        this.payout = 0;
        this.deadline = "N/A";
        this.customRequiredInfo = "None";
        this.preferedMajors = "None";
        this.donorContact = "Unknown";
    }

    /**
     * Overloaded constructor with a parameter for scholarshipName.
     * @param scholarshipName The name of the scholarship.
     */
    public Scholarship(String scholarshipName) {
        this.scholarshipName = scholarshipName;
        this.payout = 0;
        this.deadline = "N/A";
        this.disbursmentDate = "N/A";
        this.customRequiredInfo = "None";
        this.preferedMajors = "None";
        this.donorContact = "Unknown";
    }

    /**
     * Overloaded constructor with parameters for scholarshipName and payout.
     * @param scholarshipName The name of the scholarship.
     * @param payout The payout amount of the scholarship.
     */
    public Scholarship(String scholarshipName, int payout) {
        this.scholarshipName = scholarshipName;
        this.payout = payout;
        this.deadline = "N/A";
        this.customRequiredInfo = "None";
        this.preferedMajors = "None";
        this.donorContact = "Unknown";
    }

    /**
     * Overloaded constructor with parameters for scholarshipName, payout, and deadline.
     * @param scholarshipName The name of the scholarship.
     * @param payout The payout amount of the scholarship.
     * @param deadline The deadline of the scholarship.
     */
    public Scholarship(String scholarshipName, int payout, String deadline) {
        this.scholarshipName = scholarshipName;
        this.payout = payout;
        this.deadline = deadline;
        this.customRequiredInfo = "None";
        this.preferedMajors = "None";
        this.donorContact = "Unknown";
    }

    /**
     * Overloaded constructor with parameters for scholarshipName, payout, deadline, and customRequiredInfo.
     * @param scholarshipName The name of the scholarship.
     * @param payout The payout amount of the scholarship.
     * @param deadline The deadline of the scholarship.
     * @param customRequiredInfo Custom required information for the scholarship.
     */
    public Scholarship(String scholarshipName, int payout, String deadline, String customRequiredInfo) {
        this.scholarshipName = scholarshipName;
        this.payout = payout;
        this.deadline = deadline;
        this.customRequiredInfo = customRequiredInfo;
        this.preferedMajors = "None";
        this.donorContact = "Unknown";
    }

    /**
     * Overloaded constructor with parameters for scholarshipName, payout, deadline, customRequiredInfo, and preferedMajors.
     * @param scholarshipName The name of the scholarship.
     * @param payout The payout amount of the scholarship.
     * @param deadline The deadline of the scholarship.
     * @param customRequiredInfo Custom required information for the scholarship.
     * @param preferedMajors Preferred majors for the scholarship.
     */
    public Scholarship(String scholarshipName, int payout, String deadline, String customRequiredInfo,
            String preferedMajors) {
        this.scholarshipName = scholarshipName;
        this.payout = payout;
        this.deadline = deadline;
        this.customRequiredInfo = customRequiredInfo;
        this.preferedMajors = preferedMajors;
        this.donorContact = "Unknown";
    }

    /**
     * Overloaded constructor with parameters for scholarshipName, payout, deadline, disbursmentDate, customRequiredInfo, and preferedMajors.
     * @param scholarshipName The name of the scholarship.
     * @param payout The payout amount of the scholarship.
     * @param deadline The deadline of the scholarship.
     * @param disbursmentDate The disbursement date of the scholarship.
     * @param customRequiredInfo Custom required information for the scholarship.
     * @param preferedMajors Preferred majors for the scholarship.
     */
    public Scholarship(String scholarshipName, int payout, String deadline, String disbursmentDate, String customRequiredInfo,
            String preferedMajors) {
        this.scholarshipName = scholarshipName;
        this.payout = payout;
        this.deadline = deadline;
        this.disbursmentDate = disbursmentDate;
        this.customRequiredInfo = customRequiredInfo;
        this.preferedMajors = preferedMajors;
        this.donorContact = "Unknown";
    }

    /**
     * Overloaded constructor with parameters for scholarshipName, payout, deadline, disbursmentDate, customRequiredInfo, preferedMajors, and donorContact.
     * @param scholarshipName The name of the scholarship.
     * @param payout The payout amount of the scholarship.
     * @param deadline The deadline of the scholarship.
     * @param disbursmentDate The disbursement date of the scholarship.
     * @param customRequiredInfo Custom required information for the scholarship.
     * @param preferedMajors Preferred majors for the scholarship.
     * @param donorContact Contact information for the donor of the scholarship.
     */
    public Scholarship(String scholarshipName, int payout, String deadline, String disbursmentDate, String customRequiredInfo,
            String preferedMajors, String donorContact) {
        this.scholarshipName = scholarshipName;
        this.payout = payout;
        this.deadline = deadline;
        this.disbursmentDate = disbursmentDate;
        this.customRequiredInfo = customRequiredInfo;
        this.preferedMajors = preferedMajors;
        this.donorContact = donorContact;
    }

    /**
     * Overloaded constructor with parameters for scholarshipName, payout, deadline, disbursmentDate, customRequiredInfo, preferedMajors, donorContact, and eligible.
     * @param scholarshipName The name of the scholarship.
     * @param payout The payout amount of the scholarship.
     * @param deadline The deadline of the scholarship.
     * @param disbursmentDate The disbursement date of the scholarship.
     * @param customRequiredInfo Custom required information for the scholarship.
     * @param preferedMajors Preferred majors for the scholarship.
     * @param donorContact Contact information for the donor of the scholarship.
     * @param eligible Eligibility criteria for the scholarship.
     */
    public Scholarship(String scholarshipName, int payout, String deadline, String disbursmentDate, String customRequiredInfo,
            String preferedMajors, String donorContact, String eligible) {
        this.scholarshipName = scholarshipName;
        this.payout = payout;
        this.deadline = deadline;
        this.disbursmentDate = disbursmentDate;
        this.customRequiredInfo = customRequiredInfo;
        this.preferedMajors = preferedMajors;
        this.donorContact = donorContact;
        this.eligible = eligible;
    }
    ///// End Overloaded constructors for the scholarship class /////

    ///////// Start mutators and accessors /////////

    /**
     * Get the name of the scholarship.
     * @return The name of the scholarship.
     */
    public String getScholarshipName() {
        return scholarshipName;
    }

    /**
     * Set the name of the scholarship.
     * @param scholarshipName The name of the scholarship.
     */
    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }

    /**
     * Get the payout amount of the scholarship.
     * @return The payout amount of the scholarship.
     */
    public int getPayout() {
        return payout;
    }

    /**
     * Set the payout amount of the scholarship.
     * @param payout The payout amount of the scholarship.
     */
    public void setPayout(int payout) {
        this.payout = payout;
    }

    /**
     * Get the deadline of the scholarship.
     * @return The deadline of the scholarship.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Set the deadline of the scholarship.
     * @param deadline The deadline of the scholarship as a string.
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Get the disbursement date of the scholarship.
     * @return The disbursement date of the scholarship.
     */
    public String getDisbursementDate() {
        return disbursmentDate;
    }

    /**
     * Set the disbursement date of the scholarship.
     * @param disbursmentDate The disbursement date of the scholarship.
     */
    public void setDisbursementDate(String disbursmentDate) {
        this.disbursmentDate = disbursmentDate;
    }

    /**
     * Get the custom required information for the scholarship.
     * @return Custom required information for the scholarship.
     */
    public String getCustomRequiredInfo() {
        return customRequiredInfo;
    }

    /**
     * Get the donor contact information for the scholarship.
     * @return Donor contact information for the scholarship.
     */
    public String getDonorContact() {
        return donorContact;
    }

    /**
     * Set the custom required information for the scholarship.
     * @param customRequiredInfo Custom required information for the scholarship.
     */
    public void setCustomRequiredInfo(String customRequiredInfo) {
        this.customRequiredInfo = customRequiredInfo;
    }

    /**
     * Get the preferred majors for the scholarship.
     * @return Preferred majors for the scholarship.
     */
    public String getPreferedMajors() {
        return preferedMajors;
    }

    /**
     * Set the preferred majors for the scholarship.
     * @param preferedMajors Preferred majors for the scholarship.
     */
    public void setPreferedMajors(String preferedMajors) {
        this.preferedMajors = preferedMajors;
    }
    ///////// End mutators and accessors /////////

}