package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private CourseConduction courseConduction;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Student student;
    @OneToOne(cascade = CascadeType.ALL)
    private Assessment assessment;

    public Enrollment() {
    }

    public Enrollment(Student student) {
        this.student = student;
    }

    public Enrollment(Student student, Assessment assessment) {
        this.student = student;
        this.assessment = assessment;
    }



}
