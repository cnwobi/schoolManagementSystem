package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CourseAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Semester semester;
    @ManyToOne
    private Course course;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "courseAssessment")
    private List<Assessment> assessments = new ArrayList<>();

}
