package com.chukanwobi.schoolmanagementsystem.bootstrap;

import com.chukanwobi.schoolmanagementsystem.models.*;
import com.chukanwobi.schoolmanagementsystem.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.*;

@Slf4j
@Component
public class SchoolMSBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private LecturerRepository lecturerRepository;
    private StudentRepository studentRepository;
    private CourseRepo courseRepository;
    private CourseConductionRepo courseConductionRepo;


    public SchoolMSBootstrap(LecturerRepository lecturerRepository, StudentRepository studentRepository, CourseRepo courseRepository, CourseConductionRepo courseConductionRepo ) {
        this.lecturerRepository = lecturerRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.courseConductionRepo = courseConductionRepo;

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
      //  studentRepository.saveAll(studentList());
        courseRepository.saveAll(getCoursesWithPrerequisites());
        courseConductionRepo.saveAll(getCourseConductions());

        log.debug("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nLoading bootstrap data\n\n\n\n\n\n\n\n\n\n\n");



    }


    private List<CourseConduction> getCourseConductions() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Student student7 = new Student("eokoli", "Okoli", "Emeka", "e.okoli@gmail.com", "Health Sports", bCryptPasswordEncoder.encode("password"));

        Student student1 = new Student("jhoover", "Jonathan", "Hoover", "j.hoover@fbi.gov.us", "Espionage", bCryptPasswordEncoder.encode("password"));

        Student student2 = new Student("mford", "Mark", "Ford", "m.ford@mcm.org.au", "Computer Science", bCryptPasswordEncoder.encode("password"));
        Student student3 = new Student("aread", "Andrew", "Read", "a.read@mit.edu.au", "Architecture", "password");
        Student student4 = new Student("shanity", "Shawn", "Hannity", "s.hannity@foxnews.com", "Journalism", "password");
        Student student5 = new Student("dtrump", "Donald", "Trump", "d.trump@gmail.com", "Mechanical Engineering", "password");
        Student student6 = new Student("rlarossa", "Rebecca", "Larossa", "r.larossa@thegrange.edu.au", "Teaching", "password");


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
        courseConduction.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId() == 1).findFirst().get());
        courseConduction.setCourse(courses.stream().filter(course -> course.getId() == 1).findFirst().get());
        courseConductions.add(courseConduction);


        CourseConduction courseConduction1 = new CourseConduction();
        courseConduction1.setCapacity(150);
        courseConduction1.setYear(Year.of(2018));
        courseConduction1.setSemester(Semester.SECOND);
        courseConduction1.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId() == 2).findFirst().get());
        courseConduction1.setCourse(courses.stream().filter(course -> course.getId() == 2).findFirst().get());
        courseConduction1.addStudent(student1);
        courseConduction1.addStudent(student2);
        courseConduction1.addStudent(student3);
        courseConduction1.addStudent(student4);
        courseConduction1.addStudent(student5);
        courseConduction1.addStudent(student6);
        courseConduction1.addStudent(student7);
        courseConductions.add(courseConduction1);




        CourseConduction courseConduction2 = new CourseConduction();
        courseConduction2.setCapacity(200);
        courseConduction2.setSemester(Semester.SECOND);
        courseConduction2.setYear(Year.of(2018));
        courseConduction2.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId() == 2).findFirst().get());
        courseConduction2.setCourse(courses.stream().filter(course -> course.getId() == 1).findFirst().get());
        courseConduction2.addStudent(student1);
        courseConduction2.addStudent(student2);
        courseConduction2.addStudent(student3);
        courseConduction2.addStudent(student4);
        courseConduction2.addStudent(student5);
        courseConduction2.addStudent(student6);
        courseConduction2.addStudent(student7);
        courseConductions.add(courseConduction2);

        CourseConduction courseConduction3 = new CourseConduction();

        courseConduction3.setCapacity(140);
        courseConduction3.setSemester(Semester.SECOND);
        courseConduction3.setYear(Year.of(2018));
        courseConduction3.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId() == 2).findFirst().get());
        courseConduction3.setCourse(courses.stream().filter(course -> course.getId() == 3).findFirst().get());
        courseConductions.add(courseConduction3);

        CourseConduction courseConduction4 = new CourseConduction();
        courseConduction4.setCapacity(130);
        courseConduction4.setSemester(Semester.THIRD);
        courseConduction4.setYear(Year.of(2018));
        courseConduction4.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId() == 2).findFirst().get());
        courseConduction4.setCourse(courses.stream().filter(course -> course.getId() == 3).findFirst().get());
        courseConductions.add(courseConduction4);


        CourseConduction courseConduction5 = new CourseConduction();
        courseConduction5.setCapacity(20);
        courseConduction5.setSemester(Semester.SECOND);
        courseConduction5.setYear(Year.of(2018));
        courseConduction5.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId() == 2).findFirst().get());
        courseConduction5.setCourse(courses.stream().filter(course -> course.getId() == 6).findFirst().get());
        courseConductions.add(courseConduction5);


        return courseConductions;
    }

    public List<Course> getCoursesWithPrerequisites() {
        Course course1 = new Course("Introduction to mathematics", DepartmentalCode.MTH);
        Course course2 = new Course("Introduction to mathematics 2", DepartmentalCode.MTH);
        Course course3 = new Course("Advanced mathematics", DepartmentalCode.MTH);
        Course course4 = new Course("Basic Chemistry", DepartmentalCode.CHM);
        Course course5 = new Course("Advanced Chemistry", DepartmentalCode.CHM);

        //set prerequisites
        course2.getPrerequisitesCollection().add(course1);
        course2.getPrerequisitesCollection().add(course3);
        course3.getPrerequisitesCollection().add(course2);

        course5.getPrerequisitesCollection().add(course4);
        course5.getPrerequisitesCollection().add(course1);
        course5.getPrerequisitesCollection().add(course2);


        List<Course> courseHashSet = new ArrayList<>();


        courseHashSet.add(course1);
        courseHashSet.add(course2);
        courseHashSet.add(course3);
        courseHashSet.add(course4);
        courseHashSet.add(course5);

        return courseHashSet;
    }

    public Set<Student> studentList() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Student student7 = new Student("eokoli", "Okoli", "Emeka", "e.okoli@gmail.com", "Health Sports", bCryptPasswordEncoder.encode("password"));

        Student student1 = new Student("jhoover", "Jonathan", "Hoover", "j.hoover@fbi.gov.us", "Espionage", bCryptPasswordEncoder.encode("password"));

        Student student2 = new Student("mford", "Mark", "Ford", "m.ford@mcm.org.au", "Computer Science", bCryptPasswordEncoder.encode("password"));
        Student student3 = new Student("aread", "Andrew", "Read", "a.read@mit.edu.au", "Architecture", "password");
        Student student4 = new Student("shanity", "Shawn", "Hannity", "s.hannity@foxnews.com", "Journalism", "password");
        Student student5 = new Student("dtrump", "Donald", "Trump", "d.trump@gmail.com", "Mechanical Engineering", "password");
        Student student6 = new Student("rlarossa", "Rebecca", "Larossa", "r.larossa@thegrange.edu.au", "Teaching", "password");

        Set<Student> studentList = new HashSet<>(Arrays.asList(student1, student2, student3, student4, student5, student6, student7));
        return studentList;
    }


}
