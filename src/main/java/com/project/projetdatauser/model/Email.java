package com.project.projetdatauser.model;

public class Email {

    public String emailValue;
    public boolean validityFlag;

    @Override
    public String toString() {
        return "Email{" +
                "emailValue='" + emailValue + '\'' +
                ", validityFlag=" + validityFlag +
                '}';
    }

    public Email(String emailValue, boolean validityFlag) {
        this.emailValue = emailValue;
        this.validityFlag = validityFlag;
    }
}
