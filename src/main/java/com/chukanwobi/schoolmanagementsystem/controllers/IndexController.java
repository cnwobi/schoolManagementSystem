package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        if(request.isUserInRole("STUDENT")){
            return "redirect:/student";
        }
        return "/error";
    }





    @GetMapping("/default")
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
    @GetMapping (value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
@GetMapping("/denied")
    public String getError403Page(){
        return "error/error403";
}
}