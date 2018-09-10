package com.chukanwobi.schoolmanagementsystem.commands;

import lombok.Data;

@Data

public class AssessmentCommand {
    private Long id;
    private String title;
    private String feedback;
    private Double totalAchievableMarks;
    private Double obtainedMarks;
    private Long enrollmentId;


}
