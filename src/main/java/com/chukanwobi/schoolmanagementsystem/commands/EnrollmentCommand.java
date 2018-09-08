package com.chukanwobi.schoolmanagementsystem.commands;


import com.chukanwobi.schoolmanagementsystem.models.Assessment;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import lombok.*;

import java.util.List;


@Data
public class EnrollmentCommand {
    public EnrollmentCommand() {
    }

    public EnrollmentCommand(Assessment assessment) {
        assessments.add(assessment);    }
    private Long id;
    private Long courseConductionId;
    private Long studentIdentity;


    private CourseConduction courseConduction;
    private Student student;
    private List<Assessment> assessments;


}
