package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.models.Student;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    @Autowired
    @Qualifier("student")
    UserDetailsService studentDetails;

    @GetMapping("/student")
    public String getStudentWelcomePage(Model model){
        Student student = (Student) studentDetails.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("student",student);


        return "/student/view";

    }
}
