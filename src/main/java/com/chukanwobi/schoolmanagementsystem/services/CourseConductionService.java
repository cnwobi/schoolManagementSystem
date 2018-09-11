package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;

import java.util.List;

public interface CourseConductionService {
    CourseConductionCommand findCourseConductionById(Long classId);
    List<CourseConductionCommand> findCourseConductionByLecturerId(Long lecturerId);
    CourseConductionCommand findCourseConductionByIdAndLecturerId(Long CourseConductionId, Long lecturerId);
    CourseConductionCommand saveCourseConductionCommand(CourseConductionCommand conductionCommand);
    CourseConductionCommand editCapacityAndSave(CourseConductionCommand conductionCommand);
    List<CourseConductionCommand> findCurrentCourseConductionByLecturerId(Long lecturerId);
    List<CourseConductionCommand> findPastCoursesByLecturerId(Long lecturerId);
    List<CourseConductionCommand> findAll();
    List<CourseConductionCommand> findAllCurrentCourses();
void enrollStudent(StudentCommand studentCommand);
    List<CourseConductionCommand> findCourseConductionsByStudentId(Long studentId);

}
