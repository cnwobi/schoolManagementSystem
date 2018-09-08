package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Enrollment;

import java.util.List;

public interface EnrollmentService {


List<EnrollmentCommand> findEnrollmentsByStudentId(Long id);
List<EnrollmentCommand> findPastEnrollmentsByStudentId(Long id);
List<EnrollmentCommand> findCurrentEnrollmentsByStudentId(Long id);
void saveEnrollment(EnrollmentCommand enrollmentCommand);
EnrollmentCommand findEnrollmentById(Long id);

}
