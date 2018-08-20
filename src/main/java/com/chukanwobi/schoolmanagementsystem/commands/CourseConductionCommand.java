package com.chukanwobi.schoolmanagementsystem.commands;

import com.chukanwobi.schoolmanagementsystem.models.Course;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.models.Semester;
import lombok.Data;

import java.time.Year;
@Data
public class CourseConductionCommand {

    private Long id;
    private Semester semester;
    private Integer capacity;
    private Lecturer lecturer;
    private Course course;
    private Year  year;

    public CourseConductionCommand() {
    }
}
