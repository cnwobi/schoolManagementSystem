package com.chukanwobi.schoolmanagementsystem.commands;

import lombok.Getter;

@Getter
public class StudentCommand {

    private Long Id;
    private Byte[] image;
    private String username;
    private String firstName;
    private String surname;
    private String email;
    private String major;
    private String password;

    public void setId(Long Id) {
        this.Id = Id;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
