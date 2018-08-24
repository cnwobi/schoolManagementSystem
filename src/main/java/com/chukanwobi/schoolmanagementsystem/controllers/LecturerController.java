package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.services.CourseConductionService;
import com.chukanwobi.schoolmanagementsystem.services.LecturerService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class LecturerController {


    private  LecturerService lecturerService;

  private  CourseConductionService conductionService;





    public LecturerController(LecturerService lecturerService, CourseConductionService conductionService) {
        this.lecturerService = lecturerService;
        this.conductionService = conductionService;
    }

    public LecturerCommand authenticatedLecturer(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return lecturerService.findLecturerByUsername(auth.getName());
 }

    @GetMapping("/lecturer")
    public String loadLecturerDetails(){
        return "redirect:/lecturer/"+ authenticatedLecturer().getId()+"/view/courses";
    }

    @GetMapping("/lecturer/{id}/view/courses")
    public String getCourseView( @PathVariable String id , Model model){
        //use user name to load lecturer
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.debug("User: "+auth.getName());

        if(Long.valueOf(id) != authenticatedLecturer().getId()){
           return "redirect:/lecturer/"+ authenticatedLecturer().getId()+"/view/courses";
        }

        model.addAttribute("coursesConducted",conductionService .findCourseConductionByLecturerId(authenticatedLecturer().getId()));
        model.addAttribute("lecturer",lecturerService.findLecturerById(authenticatedLecturer().getId()));
        return "lecturer/dashboard";
    }


    @GetMapping("/lecturer/{lecturerId}/class/{classId}/editCapacity")
    public String EditClassCapacity(@PathVariable String lecturerId, @PathVariable String classId, Model model){
        if(Long.valueOf(lecturerId) != authenticatedLecturer().getId()){
           throw new RuntimeException("You do not have access to view or edit this class");
        }
        model.addAttribute("class",conductionService.findCourseConductionByIdAndLecturerId(Long.valueOf(classId), authenticatedLecturer().getId()));
        return "courseConduction/form";
    }

@GetMapping("/lecturer/'+${lecturerId}+'/class/'+${classId}+/'students-list")
    public String getStudentsInEachClass(@PathVariable String lecturerId,@PathVariable String classId,Model model){
        if(Long.valueOf(lecturerId)!= authenticatedLecturer().getId()){
            throw new RuntimeException("You do not have access to view or edit this class");
        }
    LecturerCommand command = lecturerService.findLecturerById(authenticatedLecturer().getId());
return "lecturer/class/students";}
}
