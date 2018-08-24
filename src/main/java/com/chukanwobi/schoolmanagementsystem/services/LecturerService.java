package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.models.Student;

import java.util.List;

public interface LecturerService {
    List<Lecturer> getLecturers();

    LecturerCommand findLecturerById(Long Id);
    

    LecturerCommand findLecturerByUsername(String username);


}
