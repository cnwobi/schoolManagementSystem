package com.chukanwobi.schoolmanagementsystem.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

   private List<EnrollmentCommand> enrollmentList = new ArrayList<>();
}
