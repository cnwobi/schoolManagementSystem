package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionAssessmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionAssessmentConverters.CourseConductionAssessmentCommandToCourseConductionAssessment;
import com.chukanwobi.schoolmanagementsystem.models.AssessmentRecord;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.CourseConductionAssessment;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseConductionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseConductionAssessmentServiceImpl implements CourseConductionAssessmentService {
    @Autowired
    private CourseConductionRepo conductionRepo;
    @Autowired

    private CourseConductionAssessmentCommandToCourseConductionAssessment converter;


    @Override
    public void saveCourseConductionAssessmentAndAddToAllStudentsInClass(CourseConductionAssessmentCommand assessment) {
CourseConduction courseConduction = conductionRepo.findById(assessment.getCourseConductionCommand().getId()).get();


CourseConductionAssessment assessment1 = converter.convert(assessment);
courseConduction.addCourseConductionAssessment(assessment1);

        courseConduction.getStudents().forEach(student -> student.addAssessmentRecord(new AssessmentRecord(assessment1)));
/*
courseConduction.getEnrollments().forEach(enrollment -> enrollment.addAssessment(new Assessment(assessment.getTitle(),assessment.getTotalAchievableMarks(),null)));*/


conductionRepo.save(courseConduction);
    }


}
