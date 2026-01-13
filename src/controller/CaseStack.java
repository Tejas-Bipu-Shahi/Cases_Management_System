/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Case;
/**
 *
 * @author Tejas Shahi
 */
public class CaseStack {
    
    private Case[] arr;    // Array to store cases 
    private int top;       // Index of the top element
    private int capacity;  // Maximum size of the stack 

    // Constructor: Initialize stack 
    public CaseStack(int size) {
        arr = new Case[size];
        capacity = size;
        top = -1; // -1 indicates empty
    }

    // Push Operation: Add element to top 
    public void push(Case x) {
        if (isFull()) {
            System.out.println("Stack OverFlow"); 
            return;
        }
        System.out.println("Inserting " + x.getCaseId()); 
        arr[++top] = x; // Increment top then add
    }

    // Pop Operation: Remove element from top 
    public Case pop() {
        if (isEmpty()) {
            System.out.println("STACK EMPTY"); 
            return null;
        }
        return arr[top--]; // Return value then decrement top
    }

    // Utility: Return current size 
    public int size() {
        return top + 1;
    }

    // Utility: Check if empty 
    public boolean isEmpty() {
        return top == -1;
    }

    // Utility: Check if full 
    public boolean isFull() {
        return top == capacity - 1;
    }
    
    // Allows us to peek at any index without removing it
    public Case get(int index) {
        if (index >= 0 && index <= top) {
            return arr[index];
        }
        return null;
    }
    
    // clear the stack 
    public void clear() {
        top = -1; // Resetting top effectively clear
    }
}