/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tejas Shahi Represents Criminal offenses involving the State and
 * Police.
 */
public class CriminalCase extends Case {

    // Attributes for Criminal Case
    private String crimeType;
    private String policeStation;
    private String firNumber;
    private String bailGranted;

    // CONSTRUCTOR
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

    // GETTERS
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

    // IMPLEMENTING ABSTRACT METHODS
    @Override
    public String getCaseType() {
        return "Criminal";
    }

    @Override
    public boolean validateCase() {
        // Police Station is mandatory for criminal cases
        if (this.policeStation == null || this.policeStation.trim().isEmpty()) {
            return false;
        }

        // FIR Number is mandatory
        if (this.firNumber == null || this.firNumber.trim().isEmpty()) {
            return false;
        }

        return true;
    }

}
