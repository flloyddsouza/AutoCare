package com.example.autoCare.Model;

public class User {

    private String personFName;
    private String personLName;
    private String gender;
    private String dob;
    private String streetAddress;
    private String stateCode;
    private String postCode;
    private String email;
    private String password;

    public User() {
    }

    public User(String personFName, String personLName, String gender, String dob, String streetAddress, String stateCode, String postCode, String email, String password) {
        this.personFName = personFName;
        this.personLName = personLName;
        this.gender = gender;
        this.dob = dob;
        this.streetAddress = streetAddress;
        this.stateCode = stateCode;
        this.postCode = postCode;
        this.email = email;
        this.password = password;
    }

    public String getPersonFName() {
        return personFName;
    }

    public void setPersonFName(String personFName) {
        this.personFName = personFName;
    }

    public String getPersonLName() {
        return personLName;
    }

    public void setPersonLName(String personLName) {
        this.personLName = personLName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
