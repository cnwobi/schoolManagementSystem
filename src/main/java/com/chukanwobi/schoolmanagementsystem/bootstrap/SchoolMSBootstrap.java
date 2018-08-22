package com.chukanwobi.schoolmanagementsystem.bootstrap;

import com.chukanwobi.schoolmanagementsystem.models.*;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseConductionRepo;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseRepository;
import com.chukanwobi.schoolmanagementsystem.repositories.LecturerRepository;
import com.chukanwobi.schoolmanagementsystem.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.*;

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
          courseRepository.saveAll(getCoursesWithPrerequisites());
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

        CourseConduction courseConduction3 = new CourseConduction();

        courseConduction3.setCapacity(140);
        courseConduction3.setSemester(Semester.SECOND);
        courseConduction3.setYear(Year.of(2018));
        courseConduction3.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId()==2).findFirst().get());
        courseConduction3.setCourse(courses.stream().filter(course -> course.getId()==3).findFirst().get());
        courseConductions.add(courseConduction3);

        CourseConduction courseConduction4 = new CourseConduction();
        courseConduction4.setCapacity(130);
        courseConduction4.setSemester(Semester.THIRD);
        courseConduction4.setYear(Year.of(2018));
        courseConduction4.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId()==2).findFirst().get());
        courseConduction4.setCourse(courses.stream().filter(course -> course.getId()==3).findFirst().get());
        courseConductions.add(courseConduction4);

        return courseConductions;
    }

public List<Course> getCoursesWithPrerequisites(){
        Course course1 = new Course("Introduction to mathematics", DepartmentalCodes.MTH);
        Course course2 = new Course("Introduction to mathematics 2", DepartmentalCodes.MTH);
        Course course3 = new Course("Advanced mathematics",DepartmentalCodes.MTH);
        Course course4 =  new Course("Basic Chemistry",DepartmentalCodes.CHM);
        Course course5 = new Course("Advanced Chemistry",DepartmentalCodes.CHM);

        //set prerequisites
    course2.getPrerequisitesCollection().add(course1);
    course3.getPrerequisitesCollection().add(course2);

    course5.getPrerequisitesCollection().add(course4);
    course5.getPrerequisitesCollection().add(course1);
    course5.getPrerequisitesCollection().add(course2);

    log.debug(course5.getPrerequisitesCollection().toString());
    List<Course> courseHashSet = new ArrayList<>();
    Student student = new Student("eokoli","Okoli","Emeka","e.okoli@gmail.com","Health Sports");



    courseHashSet.add(course1);
    courseHashSet.add(course2);
    courseHashSet.add(course3);
    courseHashSet.add(course4);
    courseHashSet.add(course5);
    log.debug(courseHashSet.toString());
    return courseHashSet;
}



}
