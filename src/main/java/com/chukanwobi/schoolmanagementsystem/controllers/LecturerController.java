package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.services.CourseConductionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class LecturerController {
    private final CourseConductionService courseConductionService;

    public LecturerController(CourseConductionService courseConductionService) {
        this.courseConductionService = courseConductionService;
    }

    @GetMapping("/lecturer/{id}/view/courses")
    public String getCourseView(@PathVariable String id , Model model){
        model.addAttribute("coursesConducted", courseConductionService.findCourseConductionByLecturerId(Long.valueOf(id)));
        return "lecturer/courseview";
    }

}