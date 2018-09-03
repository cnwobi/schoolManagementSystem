package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Semester;

import java.util.List;

public interface CourseConductionService {
    CourseConductionCommand findCourseConductionById(Long classId);
    List<CourseConductionCommand> findCourseConductionByLecturerId(Long lecturerId);
    CourseConductionCommand findCourseConductionByIdAndLecturerId(Long CourseConductionId, Long lecturerId);
    CourseConductionCommand saveCourseConductionCommand(CourseConductionCommand conductionCommand);
    CourseConductionCommand editCapacityAndSave(CourseConductionCommand conductionCommand);
    List<CourseConductionCommand> returnCourseConductionByCurrentSemesterAndYear(Long lecturerId);
    void uploadGrades(CourseConductionCommand courseConduction);
}
