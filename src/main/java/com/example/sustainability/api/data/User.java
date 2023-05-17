package com.example.sustainability.api.data;


import javax.persistence.*;

@Entity
@Table(name = "worker")
public class User {

    public User() {
    }

    public User(String firstName, String lastName, String email, String org, String password, String userToken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.org = org;
        this.password = password;
        this.userToken = userToken;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "worker_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private  String lastName;
    @Column(name = "email")
    private  String email;

    @Column(name = "org")
    private String org;

    @Column
    private String password;

    @Column
    private String userToken;

    public Long getId() {
        return id;
    }

    public String getOrg() {
        return org;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOrg(String org) {
        this.org = org;
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

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
