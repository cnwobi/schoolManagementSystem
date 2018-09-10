package com.chukanwobi.schoolmanagementsystem.commands;

import com.chukanwobi.schoolmanagementsystem.models.*;

import lombok.Data;

import java.time.Year;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

@Data
public class CourseConductionCommand {

    private Long id;
    private Semester semester;
    private Integer capacity;
    private LecturerCommand lecturer;
    private CourseCommand course;
    private Year  year;


    public CourseConductionCommand() {
    }
}
