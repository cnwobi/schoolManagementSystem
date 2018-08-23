package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.models.DepartmentalCodes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
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
