package com.chukanwobi.schoolmanagementsystem.services.security;

import com.chukanwobi.schoolmanagementsystem.models.Student;
import com.chukanwobi.schoolmanagementsystem.repositories.StudentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StudentSecurityService implements UserDetailsService {
    private StudentRepository studentRepository;

    public StudentSecurityService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      Optional<Student> optionalStudent= studentRepository.findStudentByUserName(s);
    if(!optionalStudent.isPresent()){
        throw new UsernameNotFoundException("Student not found");
    }
    return optionalStudent.get();
    }
}
