package com.chukanwobi.schoolmanagementsystem.commands;

import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private Set<CourseConduction> conductionSet = new HashSet<>();
}
