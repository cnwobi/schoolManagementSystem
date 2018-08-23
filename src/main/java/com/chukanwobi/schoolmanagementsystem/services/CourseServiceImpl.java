package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConverters.CourseToCourseCommand;
import com.chukanwobi.schoolmanagementsystem.models.Course;
import com.chukanwobi.schoolmanagementsystem.models.DepartmentalCodes;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

   @Autowired
    private CourseRepo courseRepo;
   @Autowired
   private CourseToCourseCommand converter;


    @Override
    public CourseCommand findCommandById(Long id) {
        Optional<Course> courseOptional = courseRepo.findById(id);
        if(!courseOptional.isPresent()){
            throw new RuntimeException("Course with Id: " +id);
        }
        return converter.convert(courseOptional.get());
    }

    @Override
    public List<CourseCommand> getCourses() {
        List<CourseCommand> courseCommandList = new ArrayList<>();
        courseRepo.findAll().iterator().forEachRemaining(course -> courseCommandList.add(converter.convert(course)));
        return courseCommandList;
    }

    @Override
    public List<CourseCommand> getCoursesByDeparmentalCodes(DepartmentalCodes departmentalCodes) {
        List<Course> courseList = new ArrayList<>();
        courseRepo.findAll().iterator().forEachRemaining(courseList::add);


        List<CourseCommand> courseCommandList = new ArrayList<>();
        courseList.stream().filter(course -> course.getDepartmentalCodes()==departmentalCodes).forEach(course -> courseCommandList.add(converter.convert(course)));

        return courseCommandList;
    }
}
