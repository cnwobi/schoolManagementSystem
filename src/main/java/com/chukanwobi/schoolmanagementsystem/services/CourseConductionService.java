package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;

import java.util.List;

public interface CourseConductionService {
    List<CourseConductionCommand> findCourseConductionByLecturerId(Long lecturerId);
    CourseConductionCommand findCourseConductionByIdAndLecturerId(Long CourseConductionId, Long lecturerId);
}
