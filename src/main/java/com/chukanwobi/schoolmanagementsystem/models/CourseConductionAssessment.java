package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Data
@Entity

public class CourseConductionAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CourseConduction courseConduction;

    @OneToMany(mappedBy = "courseConductionAssessment",fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private Set<AssessmentRecord> assessmentRecords =  new HashSet<>();


    private Calendar openDate;
    private Calendar DueDate;

    private String title;
    private BigDecimal totalAchievableMarks;


    public CourseConductionAssessment addAssessmentRecord(AssessmentRecord assessmentRecord){
        assessmentRecord.setCourseConductionAssessment(this);
        assessmentRecords.add(assessmentRecord);
        return this;
    }


}
