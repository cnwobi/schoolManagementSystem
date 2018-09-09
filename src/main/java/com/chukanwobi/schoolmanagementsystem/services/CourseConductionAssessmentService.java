package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.models.CourseConductionAssessment;

public interface CourseConductionAssessmentService {

    void saveCourseConductionAssessmentAndAddToAllStudentsInClass(CourseConductionAssessment assessment);

}
