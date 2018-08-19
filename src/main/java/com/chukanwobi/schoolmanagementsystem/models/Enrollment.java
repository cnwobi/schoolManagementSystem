package com.chukanwobi.schoolmanagementsystem.models;

import javax.persistence.*;

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
}
