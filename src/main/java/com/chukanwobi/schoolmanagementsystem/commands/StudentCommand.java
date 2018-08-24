package com.chukanwobi.schoolmanagementsystem.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCommand {

    private Long Id;
    private Byte[] image;
    private String username;
    private String firstName;
    private String surname;
    private String email;
    private String major;
    private String password;

}
