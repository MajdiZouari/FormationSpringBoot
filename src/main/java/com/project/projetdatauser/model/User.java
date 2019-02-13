package com.project.projetdatauser.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    @NotNull
    private String login;
    @NotNull
    private String pwd;
    private String country;
    private String civility;
    @NotNull(message = "Please provide first Name")
    private String firstName;
    private String firstName2;
    @NotNull(message = "Please provide last Name")
    private String lastName;
    private String lastName2;
    private String dateOfBirth;
    private Adresse adresse;
    @NotNull(message = "Please provide Email")
    @Valid
    private Email email;

    public User(){

    }

    public User(String login, String pwd, String country, String civility, String firstName, String firstName2, String lastName, String lastName2, String dateOfBirth, Adresse adresse, Email email) {
        this.login = login;
        this.pwd = pwd;
        this.country = country;
        this.civility = civility;
        this.firstName = firstName;
        this.firstName2 = firstName2;
        this.lastName = lastName;
        this.lastName2 = lastName2;
        this.dateOfBirth = dateOfBirth;
        this.adresse = adresse;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "country='" + country + '\'' +
                ", civility='" + civility + '\'' +
                ", firstName='" + firstName + '\'' +
                ", firstName2='" + firstName2 + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastName2='" + lastName2 + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", adresse=" + adresse +
                ", email=" + email +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName2() {
        return firstName2;
    }

    public void setFirstName2(String firstName2) {
        this.firstName2 = firstName2;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}
