package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Assessment;
import com.chukanwobi.schoolmanagementsystem.models.Course;
import com.chukanwobi.schoolmanagementsystem.models.DepartmentalCode;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseRepo;
import com.chukanwobi.schoolmanagementsystem.services.CourseConductionService;
import com.chukanwobi.schoolmanagementsystem.services.CourseService;
import com.chukanwobi.schoolmanagementsystem.services.EnrollmentService;
import com.chukanwobi.schoolmanagementsystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private CourseService courseService;
@Autowired
    private StudentService studentService;
    @Autowired
    private CourseConductionService conductionService;
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
private CourseRepo courseRepo;
    @GetMapping("/student")
    public String getProfileVIew(Model model) {
        Student student = studentService.getAuthenticatedStudent();
        model.addAttribute("student", student);
         model.addAttribute("courses",conductionService.findAllCurrentCourses());
         model.addAttribute("enrolledCourses",enrollmentService.findEnrollmentsByStudentId(student.getId()));
         model.addAttribute("currentEnrollments",enrollmentService.findCurrentEnrollmentsByStudentId(student.getId()));
         model.addAttribute("pastEnrollments",enrollmentService.findPastEnrollmentsByStudentId(student.getId()));

        return "student/view";

    }

    @GetMapping("students/view/course/{departmentalCode}")
    public String viewCourseByDepartmentalCodes(@PathVariable DepartmentalCode departmentalCode, Model model){
       model.addAttribute("coursesInDepartment" ,courseService.getCoursesByDeparmentalCodes(departmentalCode));

       return "courses/view/department";

    }
@GetMapping("student/class/{classId}/enroll")
   public String enroll(@PathVariable String classId ,Model model) {
    CourseConductionCommand courseConductionCommand = conductionService.findCourseConductionById(Long.valueOf(classId));
    Optional<Course> optionalCourse = courseRepo.findById(courseConductionCommand.getCourse().getId());
    model.addAttribute("courseConductionCommand",courseConductionCommand);
    model.addAttribute("enrollmentNew", new EnrollmentCommand(new Assessment()));
    model.addAttribute("course",optionalCourse.get());
    model.addAttribute("authStudent",studentService.getAuthenticatedStudent());

    model.addAttribute("hasEnrolled",studentService.hasEnrolledBefore(Long.valueOf(classId)));

return "student/enroll";
}

@PostMapping("student/enrollStudent")
    public String postEnroll(@ModelAttribute EnrollmentCommand enrollmentCommand){
enrollmentService.saveEnrollment(enrollmentCommand);
        return "redirect:/student";
}

@GetMapping("student/grades/{enrollmentId}" )
    public String viewGrades(@PathVariable String enrollmentId,Model model){

       model.addAttribute("enrollment", enrollmentService.findEnrollmentById(Long.valueOf(enrollmentId)));

        return "student/grades";
}
}
