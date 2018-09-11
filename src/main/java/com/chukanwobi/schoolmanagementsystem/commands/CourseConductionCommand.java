package com.chukanwobi.schoolmanagementsystem.commands;

import com.chukanwobi.schoolmanagementsystem.models.*;

import lombok.Data;

import java.time.Year;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class CourseConductionCommand {

    private Long id;
    private Semester semester;
    private Integer capacity;
    private LecturerCommand lecturer;
    private CourseCommand course;
    private Year  year;

    private Set<StudentCommand> studentCommands = new HashSet<>();


    public CourseConductionCommand() {
    }

    public Set<StudentCommand> studentsSortedByFirstName(){
      return   studentCommands.stream().sorted(Comparator.comparing(StudentCommand::getFirstName)).collect(Collectors.toSet());
    }
}
