package com.chukanwobi.schoolmanagementsystem.converters.courseConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.models.Course;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseCommand implements Converter<Course, CourseCommand> {

    @Override
    @Synchronized
    @Nullable
    public CourseCommand convert(Course course) {
        if(course==null){
            return null;
        }

        CourseCommand command = new CourseCommand();
        command.setTitle(course.getTitle());
        command.setId(course.getId());
        command.setDepartmentalCode(course.getDepartmentalCode());

        return command;
    }
}
