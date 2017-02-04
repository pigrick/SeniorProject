/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import domain.FriendRequest;
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
    private String picurl;
    private FriendRequest viewerfriend;
    private String description;
    
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

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public FriendRequest getViewerfriend() {
        return viewerfriend;
    }

    public void setViewerfriend(FriendRequest viewerfriend) {
        this.viewerfriend = viewerfriend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    
    
}
