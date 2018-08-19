package com.chukanwobi.schoolmanagementsystem.models;

import javax.persistence.*;

@Entity
public class CourseConduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
   private Semester semester;
    private Integer capacity;
    @ManyToOne
    private Lecturer lecturer;
    @ManyToOne
    private Course course;
}
