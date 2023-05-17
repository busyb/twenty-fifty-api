package com.example.sustainability.api.dto;

import com.example.sustainability.api.api.PasswordMatches;
import com.example.sustainability.api.api.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {

    private String firstName;
    private String lastName;

    private String org;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

//    @NotNull   javax.validation
//    @NotEmpty
    private String password;
    private String matchingPassword;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String org, String email, String password, String matchingPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.org = org;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
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
}

