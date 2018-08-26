package com.chukanwobi.schoolmanagementsystem.converters.courseConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.models.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CourseCommandToCourse implements Converter<CourseCommand, Course> {
    @Override
    public Course convert(CourseCommand courseCommand) {
        if(courseCommand==null){
            return null;
        }
        Course course = new Course();
        course.setTitle(courseCommand.getTitle());
        course.setId(courseCommand.getId());
        course.setDepartmentalCode(courseCommand.getDepartmentalCode());
        return course;
    }
}
