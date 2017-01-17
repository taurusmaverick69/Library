package com.maverick.domain.exam;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Authentication {

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    public Authentication(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
