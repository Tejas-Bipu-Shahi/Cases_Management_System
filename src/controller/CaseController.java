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

public class CaseController {

    // This list is static so it is shared across the entire app
    private static LinkedList<Case> allCases = new LinkedList<>();

    public CaseController() {
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
