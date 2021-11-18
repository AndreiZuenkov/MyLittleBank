package com.example.mylittlebank.persistence.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
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

}
