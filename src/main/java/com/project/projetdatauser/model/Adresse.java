package com.project.projetdatauser.model;

public class Adresse {

    public String adressLine1;
    public String city;
    public String region;
    public String postalCode;
    public String country;

    @Override
    public String toString() {
        return "Adresse{" +
                "adressLine1='" + adressLine1 + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public Adresse(String adressLine1, String city, String region, String postalCode, String country) {
        this.adressLine1 = adressLine1;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
    }
}
