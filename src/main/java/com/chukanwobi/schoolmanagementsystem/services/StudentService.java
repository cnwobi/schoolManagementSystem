package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.models.Student;

public interface StudentService {
    Student getAuthenticatedStudent();
    Boolean hasEnrolledBefore(Long courseConductionId);


}
