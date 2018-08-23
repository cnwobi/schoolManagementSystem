package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.models.DepartmentalCodes;

import java.util.List;
import java.util.Optional;

public interface CourseService {
  CourseCommand  findCommandById(Long id);
    List<CourseCommand> getCourses();
    List<CourseCommand> getCoursesByDeparmentalCodes(DepartmentalCodes departmentalCodes);
}
