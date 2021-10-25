package com.example.mylittlebank.model;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "fullname")
    private String fullName;

    private String email;
    private String phone;
    private String address;
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;

//
//    public User() {
//    }
//
//    public User(String fullName, String email, String phone, String address) {
//        this.fullName = fullName;
//        this.email = email;
//        this.phone = phone;
//        this.address = address;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
