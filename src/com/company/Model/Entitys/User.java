package com.company.Model.Entitys;

import java.security.NoSuchAlgorithmException;

public class User {
    public int id;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkPassword(String password) {
        try {
            String hash = Security.SecurityBuilder().sha1(password);
            if(hash.substring(0,30).equals(this.password))
                return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getHashedPassword() {
        try {
            String hash = Security.SecurityBuilder().sha1(password+"");
            return hash.substring(0,30);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +"\n"+
                ", username='" + username + '\'' +"\n"+
                ", password='" + password + '\'' +"\n"+
                '}';
    }
}
