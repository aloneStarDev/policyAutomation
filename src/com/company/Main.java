package com.company;

import com.company.Model.Entitys.Security;

import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Security.SecurityBuilder().sha1("amir"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
