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
    
    private final LinkedList<Case> allCases;

    public CaseController() {
        this.allCases = new LinkedList<>();
    }
    
    public boolean registerCase(Case newCase) {
        // 1. Validation: Check if ID already exists
        for (Case c : allCases) {
            if (c.getCaseId() == newCase.getCaseId()) {
                System.out.println("Error: Case ID " + newCase.getCaseId() + " already exists.");
                return false; // Stop! Do not save.
            }
        // ADD TO LINKEDLIST
        allCases.add(newCase);
        return true;
        
        }
        
        // 2. Success: Add to list
        allCases.add(newCase);
        System.out.println("Success! List size is now: " + allCases.size());
        return true; // Tell View it worked
    }
}