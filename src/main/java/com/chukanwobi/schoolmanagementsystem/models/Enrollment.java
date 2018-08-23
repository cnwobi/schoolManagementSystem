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
    private CourseConduction courseConduction;
   /* @ManyToMany(mappedBy = "enrollments")
    private List<Assessment> assessments;*/

    public Enrollment(Student student, CourseConduction courseConduction) {
        this.student = student;
        this.courseConduction = courseConduction;
    }

    public Enrollment(Semester semester, Student student, CourseConduction course) {
        this.semester = semester;
        this.student = student;
        this.courseConduction = course;
    }

    public Enrollment() {
    }
}
