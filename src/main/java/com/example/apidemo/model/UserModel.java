package com.example.apidemo.model;

import lombok.Data;

@Data
public class UserModel {
    private int id;
    private String name;
    private String state;
    private String message;

    public UserModel(int id, String name, String state, String message) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.message = message;
    }

    public UserModel(String name, String state, String message) {
        this.name = name;
        this.state = state;
        this.message = message;
    }

    public UserModel(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public UserModel(int id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}
