/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Tejas Shahi
 */
import model.Case;
import java.util.LinkedList;
import model.CivilCase;
import model.CriminalCase;

public class CaseController {

    // This list is static so it is shared across the entire app
    private static LinkedList<Case> allCases = new LinkedList<>();

    // Inside CaseController.java
    public CaseController() {
        loadPredefinedCases();
    }

    private void loadPredefinedCases() {
        // --- 3 CIVIL CASES ---
        CivilCase c1 = new CivilCase(
                101, "REG-001", "Smith vs. Doe", "2025-01-10", "2025-12-26",
                "Kamala Singh", "Open", "Property", "Land Dispute in Thamel",
                500000.0, "Ownership Transfer"
        );

        CivilCase c2 = new CivilCase(
                102, "REG-002", "ABC Corp vs. XYZ Ltd", "2025-02-01", "2025-06-20",
                "Tek Raj Joshi", "First", "Contract", "Breach of Agreement",
                120000.0, "Compensation"
        );

        CivilCase c3 = new CivilCase(
                103, "REG-003", "Family Estate Issue", "2025-03-12", "2025-07-01",
                "Babu Kaji", "Open", "Family", "Inheritance Claim",
                75000.0, "Equal Division"
        );

        // --- 2 CRIMINAL CASES ---
        CriminalCase cr1 = new CriminalCase(
                201, "CRM-999", "State vs. Rabin K.", "2025-01-05", "2025-12-26",
                "Kamala Singh", "Open", "Theft", "Durbar Marg Police",
                "FIR-1122", "Not Granted"
        );

        CriminalCase cr2 = new CriminalCase(
                202, "CRM-888", "Fraud Investigation", "2025-02-20", "2025-05-25",
                "Tek Raj Joshi", "Second", "Fraud", "Lazimpat Station",
                "FIR-3344", "Granted"
        );

        // Add them to the static list
        allCases.add(c1);
        allCases.add(c2);
        allCases.add(c3);
        allCases.add(cr1);
        allCases.add(cr2);

        System.out.println("Preloaded 5 sample cases successfully.");
    }

    // helper method to fin case by id
    // We use this in register, delete, and update to avoid rewriting the loop every time
    public Case findCaseById(int targetId) {
        if (allCases.isEmpty()) {
            return null;
        }

        // Loop through the list to find the match
        for (Case c : allCases) {
            if (c.getCaseId() == targetId) {
                return c; // Found it, return the object
            }
        }
        return null; // Didn't find anything
    }
    
    // To register any case 
    public boolean registerCase(Case newCase) {
        // First, check if this ID already exists using our helper method
        Case existingCase = findCaseById(newCase.getCaseId());

        if (existingCase != null) {
            System.out.println("Error: Case ID " + newCase.getCaseId() + " already exists.");
            return false; // Stop here, don't add duplicates
        }

        // If ID is unique, add it to the list
        allCases.add(newCase);
        return true;
    }

    // deletes case by case id
    public boolean deleteCase(int targetId) {
        Case caseToRemove = findCaseById(targetId);
        if (caseToRemove != null) {
            allCases.remove(caseToRemove);
            return true;
        }
        return false;
    }

    // replaces old case by new updated case
    public boolean updateCase(Case updatedCase) {
        Case oldCase = findCaseById(updatedCase.getCaseId());
        if (oldCase != null) {
            int index = allCases.indexOf(oldCase);
            allCases.set(index, updatedCase);
            return true;
        }
        return false;
    }

    // Just returns the whole list so the Table can display it
    public LinkedList<Case> getAllCases() {
        return allCases;
    }
}
