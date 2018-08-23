package com.chukanwobi.schoolmanagementsystem.commands;

import com.chukanwobi.schoolmanagementsystem.models.Course;

import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.models.Semester;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import lombok.Data;

import java.time.Year;

import java.util.HashSet;

import java.util.Set;

@Data
public class CourseConductionCommand {

    private Long id;
    private Semester semester;
    private Integer capacity;
    private Lecturer lecturer;
    private Course course;
    private Year  year;

    private Set<Student> enrolledStudents = new HashSet<>();
    public CourseConductionCommand() {
    }
}
