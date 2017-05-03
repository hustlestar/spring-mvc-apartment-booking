package com.hustlestar.airbnb.domain.dto;

/**
 * Created by Yauheni_Malashchytsk on 4/29/2017.
 */
public class UserDto {
    private String login;
    private String email;
    private String password;
    private String password2;

    public UserDto() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
