package com.example.apidemo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_test")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String state;
    private String message;

    public User(){

    }

    public User(int id, String name, String state, String message) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.message = message;
    }

    public User(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public User(String name, String state, String message) {
        this.name = name;
        this.state = state;
        this.message = message;
    }

    public User(String name, String state) {
        this.name = name;
        this.state = state;
    }
}

