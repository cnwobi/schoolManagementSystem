package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    private LecturerService lecturerService;

    public IndexController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping({"", "/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("lecturers", lecturerService.getLecturers());
        return "index";
    }
}