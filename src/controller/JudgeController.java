/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Judge;
import java.util.ArrayList;

/**
 *
 * @author Tejas Shahi
 */
public class JudgeController {

    // 1. DATA STRUCTURE: ArrayList (Requirement: Appropriate Data Structure) [cite: 89]
    private static ArrayList<Judge> allJudges = new ArrayList<>();

    public JudgeController() {
        // Load data only if list is empty to avoid duplicates on reload
        if (allJudges.isEmpty()) {
            loadPredefinedJudges();
        }
    }

    private void loadPredefinedJudges() {
        // Pre-loading 3 judges so you can log in immediately
        allJudges.add(new Judge(1, "Tek Raj Joshi", "9841000000", "judge1", "pass123"));
        allJudges.add(new Judge(2, "Kalpana Singh", "9842000000", "judge2", "pass123"));
        allJudges.add(new Judge(3, "Hari Krishna", "9843000000", "judge3", "pass123"));
        System.out.println("Preloaded 3 judges.");
    }

    public Judge findJudgeById(int targetId) {
        for (Judge j : allJudges) {
            if (j.getId() == targetId) {
                return j;
            }
        }
        return null;
    }

    // 2. CREATE (Register new Judge)
    public boolean registerJudge(Judge newJudge) {
        // Check if ID already exists
        if (findJudgeById(newJudge.getId()) != null) {
            return false;
        }

        // Check if Username already exists
        for (Judge j : allJudges) {
            if (j.getUsername().equalsIgnoreCase(newJudge.getUsername())) {
                return false;
            }
        }

        allJudges.add(newJudge);
        return true;
    }

    // 3. READ (Get all Judges)
    public ArrayList<Judge> getAllJudges() {
        return allJudges;
    }

    // 4. UPDATE (Modify existing Judge details)
    public boolean updateJudge(Judge updatedJudge) {
        Judge oldJudge = findJudgeById(updatedJudge.getId());
        if (oldJudge != null) {
            int index = allJudges.indexOf(oldJudge);
            allJudges.set(index, updatedJudge);
            return true;
        }
        return false;
    }

    // 5. DELETE (Remove a Judge)
    public boolean deleteJudge(int judgeId) {
        Judge judgeToRemove = findJudgeById(judgeId);
        if (judgeToRemove != null) {
            allJudges.remove(judgeToRemove);
            return true;
        }
        return false;
    }

    // VALIDATE CREDENTIALS (Login)
    public Judge loginJudge(String username, String password) {
        for (Judge j : allJudges) {
            if (j.getUsername().equals(username) && j.getPassword().equals(password)) {
                return j;
            }
        }
        return null;
    }
}
