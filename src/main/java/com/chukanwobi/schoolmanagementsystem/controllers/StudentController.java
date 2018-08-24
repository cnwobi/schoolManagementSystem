package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.models.DepartmentalCode;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import com.chukanwobi.schoolmanagementsystem.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentController {
    @Autowired
    @Qualifier("student")
    private UserDetailsService studentDetails;
    @Autowired
    private CourseService courseService;
    @GetMapping("/student/")
    public String getProfileVIew(Model model) {
        Student student = (Student) studentDetails.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("student", student);

        return "student/view";

    }

    @GetMapping("students/view/course/{departmentalCode}")
    public String viewCourseByDepartmentalCodes(@PathVariable DepartmentalCode departmentalCode, Model model){
       model.addAttribute("coursesInDepartment" ,courseService.getCoursesByDeparmentalCodes(departmentalCode));

       return "courses/view/department";

    }


}
