package com.chukanwobi.schoolmanagementsystem.converters;

import com.chukanwobi.schoolmanagementsystem.commands.AssessmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Assessment;
import com.chukanwobi.schoolmanagementsystem.repositories.EnrollmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AssessmentCommandToAssessment implements Converter<AssessmentCommand,Assessment> {
    @Autowired
private EnrollmentRepo enrollmentRepo;
    @Override
    public Assessment convert(AssessmentCommand assessmentCommand) {
        if(assessmentCommand==null){
            return null;
        }
        Assessment assessment = new Assessment();
        assessment.setId(assessmentCommand.getId());
        assessment.setTitle(assessmentCommand.getTitle());
        assessment.setFeedback(assessmentCommand.getFeedback());
        assessment.setTotalAchievableMarks(assessmentCommand.getTotalAchievableMarks());
        assessment.setObtainedMarks(assessmentCommand.getObtainedMarks());

        assessment.setEnrollment(enrollmentRepo.findById(assessmentCommand.getEnrollmentId()).get());
return assessment;
    }
}
