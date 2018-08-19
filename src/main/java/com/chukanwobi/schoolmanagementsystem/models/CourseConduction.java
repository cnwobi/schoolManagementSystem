package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
