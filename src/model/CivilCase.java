/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tejas
 */
public class CivilCase extends Case {

    // 1. SPECIFIC ATTRIBUTES (For Civil Cases)
    // ==========================================
    private String disputeType;
    private String subjectMatter;
    private double claimAmount;
    private String reliefSought;

    // 2. CONSTRUCTOR
    // ==========================================
    public CivilCase(int caseId, String registrationNumber, String caseTitle,
            String filingDate, String hearingDate, String assignedJudge, String caseStatus,
            String disputeType, String subjectMatter, double claimAmount, String reliefSought) {

        // Pass the common data up to the Parent 'Case' class
        super(caseId, registrationNumber, caseTitle, filingDate, hearingDate, assignedJudge, caseStatus);

        this.disputeType = disputeType;
        this.subjectMatter = subjectMatter;
        this.claimAmount = claimAmount;
        this.reliefSought = reliefSought;
    }

    // 3. GETTERS
    // ==========================================
    public String getDisputeType() {
        return disputeType;
    }

    public String getSubjectMatter() {
        return subjectMatter;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public String getReliefSought() {
        return reliefSought;
    }

    // 4. IMPLEMENTING ABSTRACT METHODS
    // ==========================================
    @Override
    public String getCaseType() {
        return "Civil";
    }

    @Override
    public boolean validateCase() {
        // Subject Matter is mandatory for ALL civil cases
        if (this.subjectMatter == null || this.subjectMatter.trim().isEmpty()) {
            return false;
        }

        // Cannot claim negative money
        if (this.claimAmount < 0) {
            return false;
        }

        return true;
    }
}
