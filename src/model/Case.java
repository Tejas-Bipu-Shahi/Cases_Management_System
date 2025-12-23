/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tejas Abstract Parent Class This class cannot be instantiated
 * directly; it must be extended by CivilCase or CriminalCase Polymorphism used
 * so that abstract method are rewritten in subclasses encapsulation values cant
 * be directly accept
 */
public abstract class Case {

    // 1. COMMON ATTRIBUTES (For all case types)
    // ==========================================
    private int caseId;                // Unique(Primary Key) 
    private String registrationNumber; // Official Court Reg No ("REG-2025-001")
    private String caseTitle;          // The name of the case ("State vs. Smith")
    private String filingDate;         // When the case was registered
    private String hearingDate;        // Next scheduled hearing (Mutable)
    private String assignedJudge;      // Presiding Judge (Mutable)
    private String caseStatus;         // "Open", "Closed", "Verdict Pending" (Mutable)

    // 2. CONSTRUCTOR
    // ==========================================
    public Case(int caseId, String registrationNumber, String caseTitle,
            String filingDate, String hearingDate, String assignedJudge, String caseStatus) {
        // this refers to each instance
        this.caseId = caseId;
        this.registrationNumber = registrationNumber;
        this.caseTitle = caseTitle;
        this.filingDate = filingDate;
        this.hearingDate = hearingDate;
        this.assignedJudge = assignedJudge;
        this.caseStatus = caseStatus;
    }

    // ==========================================
    // 3. GETTERS (Accessors)
    // ==========================================
    public int getCaseId() {
        return caseId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public String getFilingDate() {
        return filingDate;
    }

    public String getHearingDate() {
        return hearingDate;
    }

    public String getAssignedJudge() {
        return assignedJudge;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    // 4. SETTERS (Mutators) - Only for attributes that change!
    // ==========================================
    // Hearing dates change often (postponements)
    public void setHearingDate(String hearingDate) {
        this.hearingDate = hearingDate;
    }
    
    // Judges can be reassigned 
    public void setAssignedJudge(String assignedJudge) {
        this.assignedJudge = assignedJudge;
    }

    // Status updates as the case moves through the court system
    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    // No setters for caseId, registrationNumber, or filingDate. 
    // These are permanent records and should not be modified after creation.

    
    // 5. ABSTRACT METHODS
    // ==========================================
    /**
     * Checks if the case data is valid according to specific laws. Civil cases
     * checks for claim amounts; Criminal cases check for police details.
     *
     * @return true if valid, false otherwise.
     */
    public abstract boolean validateCase();

    /**
     * Returns the specific "Type" of the case as a String. Used for display in
     * the table (e.g., "Civil", "Criminal").
     */
    public abstract String getCaseType();
}
