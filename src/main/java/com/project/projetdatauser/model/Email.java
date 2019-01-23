package com.project.projetdatauser.model;

public class Email {

    private String emailValue;
    private boolean validityFlag;

    public Email(){

    }

    public Email(String emailValue, boolean validityFlag) {
        this.emailValue = emailValue;
        this.validityFlag = validityFlag;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailValue='" + emailValue + '\'' +
                ", validityFlag=" + validityFlag +
                '}';
    }

    public String getEmailValue() {
        return emailValue;
    }

    public void setEmailValue(String emailValue) {
        this.emailValue = emailValue;
    }

    public boolean isValidityFlag() {
        return validityFlag;
    }

    public void setValidityFlag(boolean validityFlag) {
        this.validityFlag = validityFlag;
    }
}
