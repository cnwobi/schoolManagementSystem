package com.chukanwobi.schoolmanagementsystem.commands;


import com.chukanwobi.schoolmanagementsystem.models.Assessment;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import lombok.Data;


@Data
public class EnrollmentCommand {


    private Long id;
    private CourseConduction courseConduction;
    private Student student;
    private Assessment assessment;
}
