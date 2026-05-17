package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    // stores the filename of uploaded photo
    private String photo;

    // Constructor
    public Student(int id, String name, String email, String photo) {
        this.id    = id;
        this.name  = name;
        this.email = email;
        this.photo = photo;
    }

    // Empty constructor
    public Student() {}

    // Getters and Setters
    public int getId()                { return id; }
    public void setId(int id)         { this.id = id; }

    public String getName()           { return name; }
    public void setName(String name)  { this.name = name; }

    public String getEmail()              { return email; }
    public void setEmail(String email)    { this.email = email; }

    public String getPhoto()              { return photo; }
    public void setPhoto(String photo)    { this.photo = photo; }
}