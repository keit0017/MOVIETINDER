package com.example.Salsa.model;

import java.util.ArrayList;

public class UserModel {
    String email;
    ArrayList<Upload> uploads;

    public ArrayList<Upload> getUploads() {
        return uploads;
    }

    public String getEmail() {
        return email;
    }

}
