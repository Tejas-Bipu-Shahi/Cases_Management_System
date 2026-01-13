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
public class CaseQueue {

    private int SIZE;
    private Case items[];
    private int front, rear;

    // Constructor: Initialize queue 
    public CaseQueue(int size) {
        SIZE = size;
        items = new Case[SIZE];
        front = -1;
        rear = -1;
    }
    
public boolean isFull() {
    return rear == SIZE - 1; 
}

    // Check if Queue is Empty 
    public boolean isEmpty() {
        return front == -1;
    }

    // Add Element (Slide 44)
    public void enQueue(Case element) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            if (front == -1) {
                front = 0; // Set front to 0 if adding first element
            }
            rear++;
            items[rear] = element;
            System.out.println("Inserted " + element.getCaseId());
        }
    }

    // Remove Element 
    public Case deQueue() {
        Case element;
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            element = items[front];
            if (front >= rear) {
                // Queue has only one element, so we reset the queue after deleting it.
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            System.out.println("Deleted -> " + element.getCaseId());
            return element;
        }
    }


    public Case peek(int index) {
        int actualIndex = front + index;
        if (actualIndex <= rear) {
            return items[actualIndex];
        }
        return null;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return rear - front + 1;
    }
}
