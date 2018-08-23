package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class IndexController {

    private LecturerService lecturerService;

    public IndexController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping({"", "/", "/index"})
    public String getIndex(HttpServletRequest request) {
        if (request.isUserInRole("LECTURER")){
            return "redirect:/lecturer/";
        }
        if(request.isUserInRole("ADMIN")){
            return "redirect:/admin";
        }
        return "/error";
    }

    @GetMapping("/admin")
    public String getLecturers(Model model){
        model.addAttribute("lecturers", lecturerService.getLecturers());
        return "index";
    }




    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request){
        if (request.isUserInRole("LECTURER")){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            LecturerCommand command = lecturerService.findLecturerByUsername(auth.getName());
            return "redirect:/lecturer/"+command.getId()+"/view/courses";

        }
        if(request.isUserInRole("ADMIN")){
            return "redirect:/admin";
        }
        if(request.isUserInRole("STUDENT")){
            return "redirect:/student/";
        }
      return "/error";
    }

@GetMapping("/denied")
    public String getError403Page(){
        return "error/error403";
}
}