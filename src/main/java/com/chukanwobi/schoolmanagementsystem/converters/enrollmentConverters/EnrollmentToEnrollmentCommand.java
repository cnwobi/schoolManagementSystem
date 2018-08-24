package com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters;


import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.assessmentConverters.AssessmentToAssessmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionToCourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.studentConverter.StudentToStudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Enrollment;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentToEnrollmentCommand implements Converter<Enrollment,EnrollmentCommand> {


    private AssessmentToAssessmentCommand assessmentConverter;
    {
        assessmentConverter = new AssessmentToAssessmentCommand();
    }

    @Override
    @Synchronized
    @Nullable
    public EnrollmentCommand convert(Enrollment enrollment) {
        if(enrollment==null){
            return null;
        }
        EnrollmentCommand command = new EnrollmentCommand();
        command.setId(enrollment.getId());
        command.setAssessment(assessmentConverter.convert(enrollment.getAssessment()));
        return command;
    }
}
