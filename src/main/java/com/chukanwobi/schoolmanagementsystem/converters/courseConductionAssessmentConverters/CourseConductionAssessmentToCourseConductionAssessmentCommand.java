package com.chukanwobi.schoolmanagementsystem.converters.courseConductionAssessmentConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionAssessmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionToCourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConductionAssessment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class CourseConductionAssessmentToCourseConductionAssessmentCommand implements Converter<CourseConductionAssessment,CourseConductionAssessmentCommand> {
    private final CourseConductionToCourseConductionCommand toCourseConductionCommandConverter;

    public CourseConductionAssessmentToCourseConductionAssessmentCommand(CourseConductionToCourseConductionCommand toCourseConductionCommandConverter) {
        this.toCourseConductionCommandConverter = toCourseConductionCommandConverter;
    }

    @Override
    public CourseConductionAssessmentCommand convert(CourseConductionAssessment courseConductionAssessment) {
     if(courseConductionAssessment == null) {

         return null;
     }
     CourseConductionAssessmentCommand command = new CourseConductionAssessmentCommand();
     command.setId(courseConductionAssessment.getId());

     command.setOpenDate(courseConductionAssessment.getOpenDate());
     command.setDueDate(courseConductionAssessment.getDueDate());
     command.setTitle(courseConductionAssessment.getTitle());
     command.setTotalAchievableMarks(courseConductionAssessment.getTotalAchievableMarks());
     if(courseConductionAssessment.getAssessmentRecords()!=null && courseConductionAssessment.getAssessmentRecords().size()>0){
         courseConductionAssessment.getAssessmentRecords().forEach(assessmentRecord -> command.getAssessmentRecords().add(assessmentRecord));
     }
command.setCourseConductionCommand(toCourseConductionCommandConverter.convert(courseConductionAssessment.getCourseConduction()));
     return command;
    }
}
