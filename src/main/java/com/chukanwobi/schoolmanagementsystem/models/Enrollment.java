package com.chukanwobi.schoolmanagementsystem.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Semester semester;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
    @ManyToMany(mappedBy = "enrollments")
    private List<Assessment> assessments;
}
