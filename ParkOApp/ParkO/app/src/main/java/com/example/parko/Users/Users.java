package com.example.parko.Users;

public class Users {

    public String username;
    public String password;
    public long carNumber;
    public String email;

    public Users(String username, String password, long carNumber, String email) {
        this.username = username;
        this.password = password;
        this.carNumber = carNumber;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(long carNumber) {
        this.carNumber = carNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
