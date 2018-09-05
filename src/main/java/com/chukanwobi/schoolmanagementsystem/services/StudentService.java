package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Enrollment;
import com.chukanwobi.schoolmanagementsystem.models.Student;


import java.util.List;
import java.util.Set;

public interface StudentService {
    Student getAuthenticatedStudent();
    Boolean hasEnrolledBefore(Long courseConductionId);

}
