package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;

import java.util.List;

public interface LecturerService {
    List<Lecturer> getLecturers();
    LecturerCommand findLecturerById(Long Id);
}
