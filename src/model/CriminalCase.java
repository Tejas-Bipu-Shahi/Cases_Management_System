/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tejas Shahi
 * Represents Criminal offenses involving the State and Police.
 */
public class CriminalCase extends Case {

    // 1. SPECIFIC ATTRIBUTES (For Criminal Cases)
    // ==========================================
    private String crimeType;      // "Theft", "Assault", "Fraud"
    private String policeStation;  // Where the FIR was filed
    private String firNumber;      // First I2nformation Report Number
    private String bailGranted;   // True = Out on bail, False = In Custody

    // 2. CONSTRUCTOR
    // ==========================================
    public CriminalCase(int caseId, String registrationNumber, String caseTitle,
            String filingDate, String hearingDate, String assignedJudge, String caseStatus,
            String crimeType, String policeStation, String firNumber, String bailGranted) {

        // Pass common data to Parent
        super(caseId, registrationNumber, caseTitle, filingDate, hearingDate, assignedJudge, caseStatus);

        this.crimeType = crimeType;
        this.policeStation = policeStation;
        this.firNumber = firNumber;
        this.bailGranted = bailGranted;
    }

    // ==========================================
    // 3. GETTERS
    // ==========================================
    public String getCrimeType() {
        return crimeType;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public String getFirNumber() {
        return firNumber;
    }

    public String getBailGranted() {
        return bailGranted;
    }

    // ==========================================
    // 4. IMPLEMENTING ABSTRACT METHODS
    // ==========================================
    @Override
    public String getCaseType() {
        return "Criminal";
    }

    @Override
    public boolean validateCase() {
        // Rule 1: Police Station is mandatory for criminal cases
        if (this.policeStation == null || this.policeStation.trim().isEmpty()) {
            System.err.println("Validation Error: Police Station is required for Criminal Cases.");
            return false;
        }

        // Rule 2: FIR Number is mandatory
        if (this.firNumber == null || this.firNumber.trim().isEmpty()) {
            System.err.println("Validation Error: FIR Number is required.");
            return false;
        }

        return true;
    }

}
