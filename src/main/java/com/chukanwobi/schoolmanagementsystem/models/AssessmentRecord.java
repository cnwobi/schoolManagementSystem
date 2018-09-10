package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Setter
@Getter
public class AssessmentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String feedback;
    private Double obtainedMarks;
@ManyToOne
private Student student;
@ManyToOne
    private CourseConductionAssessment courseConductionAssessment;
}
