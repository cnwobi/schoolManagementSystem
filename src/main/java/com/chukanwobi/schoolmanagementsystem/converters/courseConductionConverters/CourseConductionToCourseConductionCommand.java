package com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters.EnrollmentToEnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.studentConverter.StudentToStudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CourseConductionToCourseConductionCommand implements Converter<CourseConduction, CourseConductionCommand> {




    private EnrollmentToEnrollmentCommand enrollmentConverter;

    public CourseConductionToCourseConductionCommand(EnrollmentToEnrollmentCommand enrollmentConverter) {
        this.enrollmentConverter = enrollmentConverter;
    }

    @Override
    @Synchronized
    @Nullable
    public CourseConductionCommand convert(CourseConduction courseConduction) {
        if(courseConduction==null){
            return null;
        }
        CourseConductionCommand  command= new CourseConductionCommand();

        command.setId(courseConduction.getId());
        command.setCapacity(courseConduction.getCapacity());
        command.setCourse(courseConduction.getCourse());
        command.setLecturer(courseConduction.getLecturer());
        command.setSemester(courseConduction.getSemester());
        command.setYear(courseConduction.getYear());
        if(courseConduction.getEnrollments() !=null && courseConduction.getEnrollments().size()>0)
        {
            courseConduction.getEnrollments().forEach(enrollment -> command.getEnrollments().add(enrollmentConverter.convert(enrollment)));

        }
        return command;
    }
}
