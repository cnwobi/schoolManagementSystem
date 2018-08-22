package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminController {

    private LecturerService lecturerService;

    public AdminController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping("/admin/lecturer/{id}/view/courses")
    public String getCoursesLecturedByLecturer(@PathVariable String id , Model model, Authentication auth){
        auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdminRole = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        if(hasAdminRole){
            model.addAttribute("coursesConducted", lecturerService.findCourseConductionByLecturerId(Long.valueOf(id)));
            model.addAttribute("lecturer",lecturerService.findLecturerById(Long.valueOf(id)));
            model.addAttribute("principal",auth);
            return "lecturer/courseview";
        }
        return "/error";
    }
}
