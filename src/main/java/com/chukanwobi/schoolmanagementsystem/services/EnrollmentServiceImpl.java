package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private CourseConductionService conductionService;

    @Override
    public EnrollmentCommand findEnrollmentByCourseConductionIdAndStudentId(Long courseConductionId, Long studentId) {
        CourseConductionCommand conductionCommand = conductionService.findCourseConductionById(courseConductionId);

        return conductionCommand.getEnrollments().stream().filter(enrollmentCommand -> enrollmentCommand.getStudent().getId()==studentId).findFirst().get();
    }
}
