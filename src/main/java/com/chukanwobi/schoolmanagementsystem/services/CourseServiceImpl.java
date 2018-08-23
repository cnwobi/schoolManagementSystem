package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.models.DepartmentalCodes;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService{
    @Override
    public CourseCommand findCommandById(Long id) {

        return null;
    }

    @Override
    public List<CourseCommand> getCourses() {
        return null;
    }

    @Override
    public List<CourseCommand> getCoursesByDeparmentalCodes(DepartmentalCodes departmentalCodes) {
        return null;
    }
}
