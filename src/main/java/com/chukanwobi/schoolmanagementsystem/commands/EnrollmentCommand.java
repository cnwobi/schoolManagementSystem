package com.chukanwobi.schoolmanagementsystem.commands;


import com.chukanwobi.schoolmanagementsystem.models.Assessment;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import lombok.*;


@Data
public class EnrollmentCommand {
    public EnrollmentCommand() {
    }

    public EnrollmentCommand(Assessment assessment) {
        this.assessment = assessment;
    }
    private Long id;
    private Long courseConductionId;
    private Long studentIdentity;


    private CourseConduction courseConduction;
    private Student student;
    private Assessment assessment;


}
