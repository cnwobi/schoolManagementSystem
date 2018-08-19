package com.chukanwobi.schoolmanagementsystem.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String firstName;
    private String surname;
    private String email;
    private String major;
    private String password;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
    private List<Enrollment> enrollmentList = new ArrayList<>();
}
