package com.chukanwobi.schoolmanagementsystem.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perquisite_of")
    private Course theCourse;

    @OneToMany(mappedBy = "theCourse")
    private Set<Course> prerequisites =  new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<CourseConduction> courseConductions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<Enrollment> enrollmentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<CourseAssessment> courseAssesments = new ArrayList<>();

}
