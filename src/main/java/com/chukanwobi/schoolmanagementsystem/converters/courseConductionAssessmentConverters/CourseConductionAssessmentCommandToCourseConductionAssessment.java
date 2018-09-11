package com.chukanwobi.schoolmanagementsystem.converters.courseConductionAssessmentConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionAssessmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.CourseConductionAssessment;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseConductionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CourseConductionAssessmentCommandToCourseConductionAssessment implements Converter<CourseConductionAssessmentCommand,CourseConductionAssessment> {

 @Autowired
    private CourseConductionRepo courseConductionRepo;

    @Override
    public CourseConductionAssessment convert(CourseConductionAssessmentCommand command) {

        if(command ==null){
            return null;
        }
      CourseConductionAssessment courseConductionAssessment = new CourseConductionAssessment();

        courseConductionAssessment.setCourseConduction(courseConductionRepo.findById(command.getCourseConductionCommand().getId()).orElseThrow(()-> new RuntimeException("CourseConduction not found")));

        courseConductionAssessment.setDueDate(command.getDueDate());
        courseConductionAssessment.setOpenDate(command.getOpenDate());
        courseConductionAssessment.setTitle(command.getTitle());
        if(command.getAssessmentRecords()!=null&&command.getAssessmentRecords().size()>0){
            command.getAssessmentRecords().forEach(assessmentRecord -> courseConductionAssessment.getAssessmentRecords().add(assessmentRecord));
        }

        return courseConductionAssessment;
    }
}
