package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    @Qualifier("student")
    private UserDetailsService studentDetails;
@Autowired
private CourseConductionService conductionService;
    @Override
    public Student getAuthenticatedStudent() {

        Student student = (Student) studentDetails.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return student;
    }

    @Override
    public Boolean hasEnrolledBefore(Long courseConductionId) {

     return    getAuthenticatedStudent().getEnrollments().stream().anyMatch(enrollment -> enrollment.getCourseConduction().getCourse().getTitle().equalsIgnoreCase(conductionService.findCourseConductionById(courseConductionId).getCourse().getTitle()));

    }
}
