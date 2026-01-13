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
import model.CivilCase;
import model.CriminalCase;
import java.util.LinkedList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

// Our main CaseController class begins here
public class CaseController {

    // This will be our main storage where all the registered cases will be stored
    private static LinkedList<Case> allCases = new LinkedList<>();

    // This is where our stack will be used, this stack will be used to store deleted cases for recycle bin
    private static CaseStack deletedCasesStack = new CaseStack(5); // Size = 5

    // To create Upcoming cases table we need a queue storage
    private static CaseQueue hearingQueue = new CaseQueue(3);  // Size = 3

    // CONSTRUCTOR
    public CaseController() {
        // Only load if empty to prevent duplicates
        if (allCases.isEmpty()) {
            loadPredefinedCases();
        }
        generateUpcomingQueue();
    }

    // --- PRELOAD DATA ---
    private void loadPredefinedCases() {
        // CIVIL CASES 
        CivilCase c1 = new CivilCase(101, "REG-001", "Smith vs. Doe", "2025-01-10", "2026-01-26", "Kamala Singh", "running", "Property", "Land Dispute in Thamel", 500000.0, "Ownership Transfer");
        CivilCase c2 = new CivilCase(102, "REG-002", "ABC Corp vs. XYZ Ltd", "2025-02-01", "2026-01-14", "Tek Raj Joshi", "running", "Contract", "Breach of Agreement", 120000.0, "Compensation");
        CivilCase c3 = new CivilCase(103, "REG-003", "Family Estate Issue", "2025-03-12", "2026-01-15", "Babu Kaji", "running", "Family", "Inheritance Claim", 75000.0, "Equal Division");

        // CRIMINAL CASES 
        CriminalCase cr1 = new CriminalCase(201, "CRM-999", "State vs. Rabin K.", "2025-01-05", "2026-01-26", "Kamala Singh", "running", "Theft", "Durbar Marg Police", "FIR-1122", "Not Granted");
        CriminalCase cr2 = new CriminalCase(202, "CRM-888", "Fraud Investigation", "2025-02-20", "2026-01-25", "Tek Raj Joshi", "running", "Fraud", "Lazimpat Station", "FIR-3344", "Granted");

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
        //loops through all cases and returns the case if the id matches
        for (Case c : allCases) {
            if (c.getCaseId() == targetId) {
                return c;
            }
        }
        return null;
    }

    // REGISTER CASE 
    public boolean registerCase(Case newCase) {
        // Checks if the case id matches to any existing cases if it does it returns false
        if (findCaseById(newCase.getCaseId()) != null) {
            return false;
        }

        // Add case to the linked list
        allCases.add(newCase);
        return true;
    }

    // DELETE CASE 
    public boolean deleteCase(int targetId) {
        Case caseToRemove = findCaseById(targetId);
        if (caseToRemove != null) {
            // Check if your custom stack is full before pushing if not full pushes it into stack and removes from the linked list
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

    // Clear Recycle Bin
    public boolean clearDeletedStack() {
        if (deletedCasesStack.isEmpty()) {
            return false;
        }
        deletedCasesStack.clear();
        return true;
    }

    // Updates the case by setting the updated case in the index of previous case
    public boolean updateCase(Case updatedCase) {
        Case oldCase = findCaseById(updatedCase.getCaseId());
        if (oldCase != null) {
            int index = allCases.indexOf(oldCase);
            allCases.set(index, updatedCase);
            return true;
        }
        return false;
    }

    //Get cases from the linked list / returns the whoel linked list
    public LinkedList<Case> getAllCases() {
        return allCases;
    }

    // Sorting 
    private void sortCasesByDate(LinkedList<Case> casesList) {
        int n = casesList.size();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Outer loop ( for i = 0 to size - 1)
        for (int i = 0; i < n - 1; i++) {

            // Inner loop ( for j = 0 to size - i - 1)
            for (int j = 0; j < n - i - 1; j++) {

                Case c1 = casesList.get(j);
                Case c2 = casesList.get(j + 1);

                // DATA COMPARISON
                // We parse the dates just to compare them.
                try {
                    LocalDate d1 = LocalDate.parse(c1.getHearingDate(), formatter);
                    LocalDate d2 = LocalDate.parse(c2.getHearingDate(), formatter);

                    // LOGIC: If d1 is AFTER d2, they are in wrong order. SWAPING THEM.
                    if (d1.isAfter(d2)) {

                        // SWAP Logic 
                        casesList.set(j, c2);
                        casesList.set(j + 1, c1);
                    }
                } catch (Exception e) {
                    // Ignore sorting for invalid dates
                }
            }
        }
    }

    // Adds the case obj with date of today and after today in the queue until its full
    public void generateUpcomingQueue() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //  Create a Temporrary LinkedList
        java.util.LinkedList<Case> tempUpcomingList = new java.util.LinkedList<>();

        //  Find valid upcoming cases
        for (Case c : allCases) {
            try {
                LocalDate hearingDate = LocalDate.parse(c.getHearingDate(), formatter);

                // Check: Today/Future AND Open/Running
                if (!hearingDate.isBefore(today)) {
                    if (c.getCaseStatus().equalsIgnoreCase("Open") || c.getCaseStatus().equalsIgnoreCase("running")) {
                        tempUpcomingList.add(c);
                    }
                }
            } catch (Exception e) {
                // Skip invalid dates
            }
        }

        // SORT: PassING the list to our Bubble Sort
        sortCasesByDate(tempUpcomingList);

        // Fill the hearingQueue until it is full
        for (Case c : tempUpcomingList) {
            if (!hearingQueue.isFull()) {
                hearingQueue.enQueue(c);
            } else {
                break; // Stop if queue is full
            }
        }
    }

    // Process Next Hearing (Dequeue logic)
    public Case nextHearing() {
        return hearingQueue.deQueue();
    }

    // Getter for the View
    public CaseQueue getHearingQueue() {
        return hearingQueue;
    }

    // GET Deleted Cases / returns the whole deleted case stack
    public CaseStack getDeletedCases() {
        return deletedCasesStack;
    }

}
