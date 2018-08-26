package com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseConductionCommandToCourseConduction implements Converter<CourseConductionCommand,CourseConduction> {
    @Override
    public CourseConduction convert(CourseConductionCommand conductionCommand) {
        return null;
    }
}
