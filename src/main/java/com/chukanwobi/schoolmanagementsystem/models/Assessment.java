package com.chukanwobi.schoolmanagementsystem.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal assessmentOne;
    private BigDecimal assessmentTwo;

    @ManyToMany
    @JoinTable(name = "enrollment_assessment",
    joinColumns = @JoinColumn(name = "assessment_id"),
    inverseJoinColumns = @JoinColumn(name = "enrollment_id"))
    private List<Enrollment> enrollments =  new ArrayList<>();

    @ManyToOne
    private CourseAssessment courseAssessment;

}
