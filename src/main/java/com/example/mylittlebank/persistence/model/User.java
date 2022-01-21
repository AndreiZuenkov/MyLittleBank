package com.example.mylittlebank.persistence.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2, max = 48, message = "name should be between 2 and 30 characters")
    @Column(name = "fullname")
    private String fullName;

    @NotEmpty(message = "email cannot be empty")
    @Email
    private String email;

//    @NotEmpty(message = "phone number cannot be empty")
    @Size(min = 5, max = 12, message = "phone number should be between 5 and 12 characters")
    private String phone;

    @NotEmpty(message = "address cannot be empty")
    private String address;

    @Past
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

}
