/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tejas Abstract Parent Class
 */
public abstract class Case {

    // Common Attributes of every cases
    private int caseId;
    private String registrationNumber;
    private String caseTitle;
    private String filingDate;
    private String hearingDate;
    private String assignedJudge;
    private String caseStatus;
    private String evidenceFilePath;

    // Initializing Constructor
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

    // 3. GETTERS (Accessors)
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

    //method to validate case
    public abstract boolean validateCase();

    // to get the Case Type 
    public abstract String getCaseType();
}
