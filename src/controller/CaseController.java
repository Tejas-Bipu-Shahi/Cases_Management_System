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

    // MAIN STORAGE: LinkedList (Keeps all active cases)
    private static LinkedList<Case> allCases = new LinkedList<>();

    // RECYCLE BIN: Custom Stack (array based stack)
    private static CaseStack deletedCasesStack = new CaseStack(5);

    // CONSTRUCTOR
    public CaseController() {
        // Only load if empty to prevent duplicates
        if (allCases.isEmpty()) {
            loadPredefinedCases();
        }
    }

    // --- PRELOAD DATA ---
    private void loadPredefinedCases() {
        // CIVIL CASES 
        CivilCase c1 = new CivilCase(101, "REG-001", "Smith vs. Doe", "2025-01-10", "2025-12-26", "Kamala Singh", "running", "Property", "Land Dispute in Thamel", 500000.0, "Ownership Transfer");
        CivilCase c2 = new CivilCase(102, "REG-002", "ABC Corp vs. XYZ Ltd", "2025-02-01", "2025-06-20", "Tek Raj Joshi", "closed", "Contract", "Breach of Agreement", 120000.0, "Compensation");
        CivilCase c3 = new CivilCase(103, "REG-003", "Family Estate Issue", "2025-03-12", "2025-07-01", "Babu Kaji", "running", "Family", "Inheritance Claim", 75000.0, "Equal Division");

        // CRIMINAL CASES 
        CriminalCase cr1 = new CriminalCase(201, "CRM-999", "State vs. Rabin K.", "2025-01-05", "2025-12-26", "Kamala Singh", "running", "Theft", "Durbar Marg Police", "FIR-1122", "Not Granted");
        CriminalCase cr2 = new CriminalCase(202, "CRM-888", "Fraud Investigation", "2025-02-20", "2025-05-25", "Tek Raj Joshi", "closed", "Fraud", "Lazimpat Station", "FIR-3344", "Granted");

        // Add to main list
        allCases.add(c1);
        allCases.add(c2);
        allCases.add(c3);
        allCases.add(cr1);
        allCases.add(cr2);
    }

    // Find Case by ID 
    public Case findCaseById(int targetId) {
        if (allCases.isEmpty()) {
            return null;
        }
        for (Case c : allCases) {
            if (c.getCaseId() == targetId) {
                return c;
            }
        }
        return null;
    }

    // REGISTER CASE 
    public boolean registerCase(Case newCase) {
        if (findCaseById(newCase.getCaseId()) != null) {
            return false;
        }

        // Add to Main List only
        allCases.add(newCase);

        return true;
    }

    // DELETE CASE 
    public boolean deleteCase(int targetId) {
        Case caseToRemove = findCaseById(targetId);
        if (caseToRemove != null) {
            // Check if your custom stack is full before pushing
            if (!deletedCasesStack.isFull()) {
                deletedCasesStack.push(caseToRemove);
                allCases.remove(caseToRemove);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // RESTORE CASE 
    public boolean restoreCase() {
        if (deletedCasesStack.isEmpty()) {
            return false;
        }

        Case restoredCase = deletedCasesStack.pop();

        if (restoredCase != null) {
            allCases.add(restoredCase);
            return true;
        }
        return false;
    }

    // --- CLEAR RECYCLE BIN ---
    public boolean cleardDeletedStack() {
        if (deletedCasesStack.isEmpty()) {
            return false;
        }
        deletedCasesStack.clear();
        return true;
    }

    // GET Deleted Cases 
    public CaseStack getDeletedCases() {
        return deletedCasesStack;
    }

    public boolean updateCase(Case updatedCase) {
        Case oldCase = findCaseById(updatedCase.getCaseId());
        if (oldCase != null) {
            int index = allCases.indexOf(oldCase);
            allCases.set(index, updatedCase);
            return true;
        }
        return false;
    }

    //Get cases from the linked list
    public LinkedList<Case> getAllCases() {
        return allCases;
    }
}
