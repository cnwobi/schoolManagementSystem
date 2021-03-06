package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.services.CourseConductionService;
import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
@Slf4j
public class LecturerController {


    private LecturerService lecturerService;

    private CourseConductionService conductionService;



    public LecturerController(LecturerService lecturerService, CourseConductionService conductionService) {
        this.lecturerService = lecturerService;
        this.conductionService = conductionService;

    }

    public LecturerCommand authenticatedLecturer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return lecturerService.findLecturerByUsername(auth.getName());
    }

    private boolean isAuthenticatedId(Long id) {
        if (authenticatedLecturer().getId() != id) {
            throw new RuntimeException("You do not have access to view or edit this class");
        }
        return true;
    }

    @GetMapping("/lecturer")
    public String loadLecturerDetails() {
        return "redirect:/lecturer/" + authenticatedLecturer().getId() + "/view/courses";
    }

    @GetMapping("/lecturer/{id}/view/courses")
    public String getCourseView(@PathVariable String id, Model model) {
        //use user name to load lecturer
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.debug("User: " + auth.getName());

        if (Long.valueOf(id) != authenticatedLecturer().getId()) {
            return "redirect:/lecturer/" + authenticatedLecturer().getId() + "/view/courses";
        }

        model.addAttribute("coursesConducted", conductionService.findCourseConductionByLecturerId(authenticatedLecturer().getId()));
        model.addAttribute("lecturer", lecturerService.findLecturerById(authenticatedLecturer().getId()));
        model.addAttribute("currentCourses", conductionService.findCurrentCourseConductionByLecturerId(authenticatedLecturer().getId()));
        model.addAttribute("pastCourses", conductionService.findPastCoursesByLecturerId(authenticatedLecturer().getId()));
        return "lecturer/dashboard";
    }


    @GetMapping("/lecturer/{lecturerId}/class/{classId}/editCapacity")
    public String EditClassCapacity(@PathVariable String lecturerId, @PathVariable String classId, Model model) {
        if (isAuthenticatedId(Long.valueOf(lecturerId)))

            model.addAttribute("capacity", conductionService.findCourseConductionByIdAndLecturerId(Long.valueOf(classId), authenticatedLecturer().getId()).getCapacity());

        model.addAttribute("courseConduction", conductionService.findCourseConductionByIdAndLecturerId(Long.valueOf(classId), authenticatedLecturer().getId()));
        return "lecturer/class/edit-capacity";
    }

    @PostMapping("/lecturer/class/edit-capacity")
    public String postEditClassCapacityForm(@ModelAttribute CourseConductionCommand conductionCommand) {
        CourseConductionCommand courseConductionCommand = conductionService.editCapacityAndSave(conductionCommand);
        return "redirect:/lecturer/" + courseConductionCommand.getLecturer().getId() + "/view/courses";
    }

    @GetMapping("/lecturer/{lecturerId}/class/{classId}/students-list")
    public String ViewStudentsEnrolledInAClass(@PathVariable String lecturerId, @PathVariable String classId, Model model) {
        CourseConductionCommand courseConduction = conductionService.findCourseConductionById(Long.valueOf(classId));
        if (isAuthenticatedId(courseConduction.getLecturer().getId()))
            model.addAttribute("courseConduction", courseConduction);
        return "lecturer/class/students";
    }



    @GetMapping("lecturer/class/{courseConductionId}/upload-grades")
    public String EditorUploadGrades(@PathVariable String courseConductionId, Model model) {
        CourseConductionCommand command = conductionService.findCourseConductionById(Long.valueOf(courseConductionId));
        if (isAuthenticatedId(Long.valueOf(command.getLecturer().getId())))
            model.addAttribute("courseConduction", command);
        return "lecturer/class/upload-grades";
    }

    @PostMapping("/lecturer/class/upload-grades")
    public String postUploadGrades(@ModelAttribute CourseConductionCommand conductionCommand) {

        lecturerService.uploadGrades(conductionCommand);

        return "redirect:/lecturer/" + conductionCommand.getLecturer().getId() + "/class/" + conductionCommand.getId() + "/students-list";
    }
}
