package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class AssessmentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String feedback;
    private BigDecimal obtainedMarks;
    @ManyToOne
    private Student student;
    @ManyToOne
    private CourseConductionAssessment courseConductionAssessment;
    private Integer numberOfTries;
    private Boolean success;

    public AssessmentRecord(CourseConductionAssessment courseConductionAssessment) {
        this.courseConductionAssessment = courseConductionAssessment;
    }

    public AssessmentRecord() {
    }
}
