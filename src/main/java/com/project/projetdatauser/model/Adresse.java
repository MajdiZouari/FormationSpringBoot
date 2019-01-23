package com.project.projetdatauser.model;

public class Adresse {

    private String adressLine1;
    private String city;
    private String region;
    private String postalCode;
    private String country;

    public Adresse(){

    }

    public Adresse(String adressLine1, String city, String region, String postalCode, String country) {
        this.adressLine1 = adressLine1;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
    }

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

    public String getAdressLine1() {
        return adressLine1;
    }

    public void setAdressLine1(String adressLine1) {
        this.adressLine1 = adressLine1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
