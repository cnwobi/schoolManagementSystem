package com.chukanwobi.schoolmanagementsystem.bootstrap;

import com.chukanwobi.schoolmanagementsystem.models.Course;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.models.Semester;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseConductionRepo;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseRepository;
import com.chukanwobi.schoolmanagementsystem.repositories.LecturerRepository;
import com.chukanwobi.schoolmanagementsystem.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SchoolMSBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private LecturerRepository lecturerRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private CourseConductionRepo courseConductionRepo;

    public SchoolMSBootstrap(LecturerRepository lecturerRepository, StudentRepository studentRepository,
                             CourseRepository courseRepository,CourseConductionRepo courseConductionRepo) {
        this.lecturerRepository = lecturerRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.courseConductionRepo = courseConductionRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

          courseConductionRepo.saveAll(getCourseConductions());
        log.debug("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nLoading bootstrap data\n\n\n\n\n\n\n\n\n\n\n");

    }


    private List<CourseConduction> getCourseConductions(){
        List<CourseConduction> courseConductions = new ArrayList<>();
        List<Lecturer> lecturers = new ArrayList<>();
        lecturerRepository.findAll().iterator()
                .forEachRemaining(lecturer -> lecturers.add(lecturer));

        List<Course> courses = new ArrayList<>();

        courseRepository.findAll().iterator().forEachRemaining(course -> courses.add(course));

    CourseConduction courseConduction = new CourseConduction();
    courseConduction.setCapacity(60);
    courseConduction.setSemester(Semester.FIRST);
    courseConduction.setYear(Year.of(2014));
    courseConduction.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId()==1).findFirst().get());
    courseConduction.setCourse(courses.stream().filter(course -> course.getId()==1).findFirst().get());
    courseConductions.add(courseConduction);




        CourseConduction courseConduction1 = new CourseConduction();
        courseConduction1.setCapacity(150);
        courseConduction1.setYear(Year.of(2017));
        courseConduction1.setSemester(Semester.SECOND);
        courseConduction1.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId()==2).findFirst().get());
        courseConduction1.setCourse(courses.stream().filter(course -> course.getId()==2).findFirst().get());
        courseConductions.add(courseConduction1);



        CourseConduction courseConduction2 = new CourseConduction();
        courseConduction2.setCapacity(200);
        courseConduction2.setSemester(Semester.SECOND);
        courseConduction2.setYear(Year.of(2018));
        courseConduction2.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId()==2).findFirst().get());
        courseConduction2.setCourse(courses.stream().filter(course -> course.getId()==2).findFirst().get());
        courseConductions.add(courseConduction2);

        return courseConductions;
    }


}
