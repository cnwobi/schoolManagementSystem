package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.models.Assessment;
import com.chukanwobi.schoolmanagementsystem.models.Course;
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


    @Override
    public void saveCourseConductionAssessmentAndAddToAllStudentsInClass(CourseConductionAssessment assessment) {
CourseConduction courseConduction = conductionRepo.findById(assessment.getCourseConduction().getId()).get();


courseConduction.getEnrollments().forEach(enrollment -> enrollment.addAssessment(new Assessment(assessment.getTitle(),assessment.getTotalAchievableMarks(),null)));

        courseConduction.addCourseConductionAssessment(assessment);
conductionRepo.save(courseConduction);
    }


}
