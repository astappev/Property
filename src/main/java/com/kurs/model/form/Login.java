package com.kurs.model.form;

public class Login {

    private String email;
    private String password;

    public void reset() {
        this.email = null;
        this.password = null;
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
