/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;

/**
 *
 * @author yeerick
 */
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private ArrayList<BlogEntry> blogEntries;
    
    public User(){}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<BlogEntry> getBlogEntries() {
        return blogEntries;
    }

    public void setBlogEntries(ArrayList<BlogEntry> blogEntries) {
        this.blogEntries = blogEntries;
    }
    
    
}
