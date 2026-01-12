/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tejas Shahi
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Judge {
    
    // 1. ATTRIBUTES
    private int id;
    private String name;
    private String contact;     
    private String username;   // For Login
    private String password;   // For Login

    // 2. CONSTRUCTOR
    public Judge(int id, String name, String contact, String username, String password) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.username = username;
        this.password = password;
    }

    // 3. GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return this.name;
    }
}