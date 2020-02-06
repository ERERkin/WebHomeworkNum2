package com.DB;

import java.sql.Date;

public class User{
    private int id;
    private String login;
    private String email;
    private String password;
    private Date dateOfRegistration;

    public User(String login, String email, String password) {
        //this.id = id;
        this.login = login;
        this.email = email;
        this.password = encrypt(password);
        //this.dateOfRegistration = dateOfRegistration;
    }

    static String encrypt(String p){
        String rev = reversPassword(p);
        return rev + rev.charAt(p.length() - 1);
    }

    static String reversPassword(String p){
        String ans = "";
        for(int i = p.length() - 1; i >= 0; i--){
            ans += p.charAt(i);
        }
        return ans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        this.password = encrypt(password);
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }
}
