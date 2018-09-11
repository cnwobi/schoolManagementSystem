package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionAssessmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConductionAssessment;

public interface CourseConductionAssessmentService {

    void saveCourseConductionAssessmentAndAddToAllStudentsInClass(CourseConductionAssessmentCommand assessment);

}
