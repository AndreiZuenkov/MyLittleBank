package com.example.mylittlebank.controller.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserDto {

    private long id;

    @NotEmpty(message = "name cannot be empty")
    private String fullName;

    @NotEmpty(message = "email cannot be empty")
    @Email
    private String email;


    @Size(min = 5, max = 12, message = "phone number should be between 5 and 12 characters")
    private String phone;

    @NotEmpty(message = "address cannot be empty")
    private String address;

    @Past
    private LocalDate dateOfBirth;

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
