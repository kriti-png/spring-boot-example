package com.example.springboot;

public class User {
    String username;
    String password;

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        if (o == this) {
            return true;
        }

        User u = (User) o;
        if (u.getUsername().equals(this.getUsername()) && u.getPassword().equals(this.getPassword())) {
            return true;
        }
        return false;
    }
}