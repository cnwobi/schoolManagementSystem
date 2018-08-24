package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.models.DepartmentalCode;

import java.util.List;

public interface CourseService {
  CourseCommand  findCommandById(Long id);
    List<CourseCommand> getCourses();
    List<CourseCommand> getCoursesByDeparmentalCodes(DepartmentalCode departmentalCode);
}
