package com.chukanwobi.schoolmanagementsystem.commands;


import lombok.Data;


@Data
public class EnrollmentCommand {


    private Long id;

    private CourseConductionCommand courseConduction;

    private StudentCommand student;

}
