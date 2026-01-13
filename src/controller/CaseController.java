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
        if (findCaseById(newCase.getCaseId()) != null) {
            return false;
        }

        // 1. Add to Main List
        allCases.add(newCase);
        
        // 2. REFRESH QUEUE
        // Instead of manually checking dates here, just run the generator!
        // This ensures the new case gets SORTED correctly into the top 3.
        generateUpcomingQueue(); 
        
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

// This method finds upcoming cases, sorts them, and puts them in the queue
    public void generateUpcomingQueue() {
        // First, I need to clear the old queue so I don't get duplicates
        hearingQueue.clear();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // I'm using a temporary list to hold valid cases so I can sort them before adding to queue
        java.util.LinkedList<Case> tempUpcomingList = new java.util.LinkedList<>();

        // Loop through all my cases to find the ones for the dashboard
        for (Case c : allCases) {
            try {
                LocalDate hearingDate = LocalDate.parse(c.getHearingDate(), formatter);

                // Filter 1: Date must be Today or in the Future
                if (!hearingDate.isBefore(today)) {
                    // Filter 2: Case must be Open or Running (Closed cases don't need hearings)
                    if (c.getCaseStatus().equalsIgnoreCase("Open")
                            || c.getCaseStatus().equalsIgnoreCase("running")) {

                        tempUpcomingList.add(c);
                    }
                }
            } catch (Exception e) {
                System.out.println("Skipping invalid date case: " + c.getCaseId());
            }
        }

        // Now I sort the list using my Bubble Sort method above!
        sortCasesByDate(tempUpcomingList);

        // Finally, fill the queue with the top cases (until the queue is full)
        for (Case c : tempUpcomingList) {
            if (!hearingQueue.isFull()) {
                hearingQueue.enQueue(c);
            } else {
                break; // Stop if queue size limit is reached
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
    
    // SEARCHING ALGORTHIMS 
    public java.util.LinkedList<Case> linearSearch(String query) {
        java.util.LinkedList<Case> results = new java.util.LinkedList<>();
        String lowerQuery = query.toLowerCase().trim();

        // Slide 9: "for (int i = 0; i < n; i++)"
        for (int i = 0; i < allCases.size(); i++) {
            Case c = allCases.get(i);
            
            // Slide 10: "if (a[i] == val)" - We check if string contains the query
            if (c.getCaseTitle().toLowerCase().contains(lowerQuery) ||
                c.getAssignedJudge().toLowerCase().contains(lowerQuery) ||
                c.getCaseType().toLowerCase().contains(lowerQuery)) {
                
                results.add(c);
            }
        }
        return results;
    }
    
    public Case binarySearchById(int targetId) {
        // 1. Sort Data First (Slide 24 says: "sorted data is required")
        sortCasesById(); 

        // Slide 14: Initialize Low and High
        int low = 0;
        int high = allCases.size() - 1;

        while (low <= high) {
            // Slide 15: Find mid value
            int mid = (low + high) / 2;
            Case midCase = allCases.get(mid);

            // Slide 16: Check if match found
            if (midCase.getCaseId() == targetId) {
                return midCase; // Value found
            }

            // Slide 18: If Value > arr[mid], set low = mid + 1
            if (midCase.getCaseId() < targetId) {
                low = mid + 1;
            } 
            // Slide 17: If Value < arr[mid], set high = mid - 1
            else {
                high = mid - 1;
            }
        }
        
        return null; // Slide 23: "If search value is not in the list return -1" (or null)
    }
    
    private void sortCasesById() {
        int n = allCases.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (allCases.get(j).getCaseId() > allCases.get(j + 1).getCaseId()) {
                    Case temp = allCases.get(j);
                    allCases.set(j, allCases.get(j + 1));
                    allCases.set(j + 1, temp);
                }
            }
        }
    }

}
