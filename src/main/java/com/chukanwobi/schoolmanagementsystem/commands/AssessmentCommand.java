package com.chukanwobi.schoolmanagementsystem.commands;

import com.chukanwobi.schoolmanagementsystem.models.Enrollment;
import lombok.Data;

@Data

public class AssessmentCommand {
    private Long id;
    private Double assessmentOne;
    private Double assessmentTwo;
    private Long enrollmentId;


}
