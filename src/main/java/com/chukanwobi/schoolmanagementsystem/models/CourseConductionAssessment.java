package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity

public class CourseConductionAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CourseConduction courseConduction;

    private String title;
    private Double totalAchievableMarks;



}
