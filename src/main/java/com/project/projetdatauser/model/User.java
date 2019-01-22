package com.project.projetdatauser.model;

public class User {

    public String country;
    public String civility;
    public String firstName;
    public String firstName2;
    public String lastName;
    public String lastName2;
    public String dateOfBirth;
    public Adresse adresse;
    public Email email;

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

    public User(String country, String civility, String firstName, String firstName2, String lastName, String lastName2, String dateOfBirth, Adresse adresse, Email email) {
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
}
