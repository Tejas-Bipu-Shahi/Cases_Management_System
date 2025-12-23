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
    
    // shared all over the app
    private static LinkedList<Case> allCases = new LinkedList<>();

    public CaseController() {
        
    }
    
    public boolean registerCase(Case newCase) {
        // Check if ID already exists
        for (Case c : allCases) {
            if (c.getCaseId() == newCase.getCaseId()) {
                System.out.println("Error: Case ID " + newCase.getCaseId() + " already exists.");
                return false; 
            }
        }
        
        
        //  Add to list
        allCases.add(newCase);
        return true; 
    }
    
    // Helper to get the list (for Admin View)
    public LinkedList<Case> getAllCases() {
        return allCases;
    }
}