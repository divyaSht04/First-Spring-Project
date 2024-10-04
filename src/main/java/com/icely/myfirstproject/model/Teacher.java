package com.icely.myfirstproject.model;

import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "First Name is required.")
    private String firstName;

    @NotEmpty(message = "Last Name is required.")
    private String lastName;

    @NotEmpty(message = "Email is required.")
    @Email(message = "Email should be valid.")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Phone Number is required.")
    private String phoneNumber;

    @NotEmpty(message = "Password is required.")
    @Size(min = 6, message = "Password must be at least 6 characters.")
    private String password;

    @NotEmpty(message = "Confirm Password is required.")
    private String confirmPassword;
    private String role;

    public Teacher() {
    }

    public Teacher(int id, String firstName, String lastName, String email, String phoneNumber, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
