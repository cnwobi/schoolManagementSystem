package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import com.chukanwobi.schoolmanagementsystem.services.security.LecturerSecurityService;
import lombok.extern.slf4j.Slf4j;
import netscape.security.Principal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class LecturerController {

    private final LecturerService lecturerService;


    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;

    }

    @GetMapping("/lecturer")
    public String loadLecturerDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LecturerCommand command = lecturerService.findLecturerByUsername(auth.getName());
        return "redirect:/lecturer/"+command.getId()+"/view/courses";
    }

    @GetMapping("/lecturer/{id}/view/courses")
    public String getCourseView( @PathVariable String id , Model model,Authentication auth){
        auth = SecurityContextHolder.getContext().getAuthentication();

        LecturerCommand command = lecturerService.findLecturerByUsername(auth.getName());
        if(Long.valueOf(id)!=command.getId()){
           return "redirect:/lecturer/"+command.getId()+"/view/courses";
        }
        log.debug("memn");
        model.addAttribute("coursesConducted", lecturerService.findCourseConductionByLecturerId(command.getId()));
        model.addAttribute("lecturer",lecturerService.findLecturerById(command.getId()));
        return "lecturer/dashboard";
    }



}
