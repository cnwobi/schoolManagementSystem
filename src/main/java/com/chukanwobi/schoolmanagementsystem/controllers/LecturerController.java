package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController( LecturerService lecturerService) {

        this.lecturerService = lecturerService;
    }

    @GetMapping("/lecturer/{id}/view/courses")
    public String getCourseView(@PathVariable String id , Model model){
        model.addAttribute("coursesConducted", lecturerService.findCourseConductionByLecturerId(Long.valueOf(id)));
        model.addAttribute("lecturer",lecturerService.findLecturerById(Long.valueOf(id)));
        return "lecturer/courseview";
    }

    @GetMapping("/lecturer/{lecturerId}/class/{classId}/editCapacity")
    public String editClassCapacity(@PathVariable String lecturerId,@PathVariable String classId,Model model){
       model.addAttribute("class",lecturerService.findCourseConductionByIdAndLecturerId(Long.valueOf(classId),Long.valueOf(lecturerId)));
       return "courseConductions/form";

    }

}
