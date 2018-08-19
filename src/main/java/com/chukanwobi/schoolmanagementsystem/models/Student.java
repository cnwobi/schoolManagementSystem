package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Student {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte[] image;
    private Long Id;
    private String firstName;
    private String surname;
    private String email;
    private String major;
    private String password;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
    private List<Enrollment> enrollmentList = new ArrayList<>();


    public Student enroll(Enrollment enrollment){
        enrollment.setStudent(this);
        this.enrollmentList.add(enrollment);
        return this;

    }
}
