package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.services.CourseConductionService;
import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CourseConductionController {
private CourseConductionService conductionService;

    public CourseConductionController(CourseConductionService conductionService) {
        this.conductionService = conductionService;
    }

    @GetMapping("/lecturer/{lecturerId}/class/{classId}/editCapacity")
    public String viewCourseConduction(@PathVariable String lecturerId, @PathVariable String classId, Model model){

    model.addAttribute("class",conductionService.findCourseConductionByIdAndLecturerId(Long.valueOf(classId),Long.valueOf(lecturerId)));
    return "courseConduction/form";
    }
}
