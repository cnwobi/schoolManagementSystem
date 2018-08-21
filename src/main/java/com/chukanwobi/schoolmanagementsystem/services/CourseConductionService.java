package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;

import java.util.List;

public interface CourseConductionService {
List<CourseConductionCommand> findCourseConductionByLecturerId(Long lecturerId);
CourseConductionCommand findCourseConductionByIdAndLecturerId(Long id,Long lecturerid);
}
