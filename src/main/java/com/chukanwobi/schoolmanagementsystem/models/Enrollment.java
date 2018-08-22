package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Semester semester;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne
    private Course course;
    @ManyToMany(mappedBy = "enrollments")
    private List<Assessment> assessments;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Enrollment(Semester semester, Student student, Course course) {
        this.semester = semester;
        this.student = student;
        this.course = course;
    }

    public Enrollment() {
    }
}
