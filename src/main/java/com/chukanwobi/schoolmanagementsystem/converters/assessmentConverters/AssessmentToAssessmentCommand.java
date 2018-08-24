package com.chukanwobi.schoolmanagementsystem.converters.assessmentConverters;

import com.chukanwobi.schoolmanagementsystem.commands.AssessmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters.EnrollmentToEnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Assessment;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AssessmentToAssessmentCommand implements Converter<Assessment,AssessmentCommand> {
   @Autowired
    EnrollmentToEnrollmentCommand enrollmentConverter;

    @Override
    @Synchronized
    @Nullable
    public AssessmentCommand convert(Assessment assessment) {
        if(assessment==null){
            return null;
        }
        AssessmentCommand command = new AssessmentCommand();
        command.setId(assessment.getId());
        command.setAssessmentOne(assessment.getAssessmentOne());
        command.setAssessmentTwo(assessment.getAssessmentTwo());

        return command;
    }
}
