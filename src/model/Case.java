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

    // 1. COMMON ATTRIBUTES (For all case types)
    // ==========================================
    private int caseId;                
    private String registrationNumber;
    private String caseTitle;         
    private String filingDate;         
    private String hearingDate;        
    private String assignedJudge;      
    private String caseStatus;         

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
    public abstract boolean validateCase();

    /**
     * Returns the specific "Type" of the case as a String. Used for display in
     * the table (e.g., "Civil", "Criminal").
     */
    public abstract String getCaseType();
}
