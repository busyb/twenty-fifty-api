package com.example.sustainability.api.dto;

public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String token;

    private boolean hasError = false;

    private String errorMessage;


    public UserResponseDto(boolean hasError, String errorMessage) {
        this.hasError = hasError;
        this.errorMessage = errorMessage;
    }

    public UserResponseDto(Long id, String firstName, String lastName, String email, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
