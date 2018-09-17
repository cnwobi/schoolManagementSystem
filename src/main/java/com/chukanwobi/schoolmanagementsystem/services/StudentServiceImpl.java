package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import com.chukanwobi.schoolmanagementsystem.repositories.StudentRepository;
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
    @Qualifier("customUserDetails")
    private UserDetailsService studentDetails;
@Autowired
    private StudentRepository studentRepository;
@Autowired
private CourseConductionService conductionService;
    @Override
    public Student getAuthenticatedStudent() {

        Student student = studentRepository.findStudentByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(()-> new RuntimeException("Student not found"));
        return student;
    }

    @Override
    public Boolean hasEnrolledBefore(Long courseConductionId) {

     return    null;

    }
}
