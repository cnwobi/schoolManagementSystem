package com.chukanwobi.schoolmanagementsystem.bootstrap;

import com.chukanwobi.schoolmanagementsystem.models.*;
import com.chukanwobi.schoolmanagementsystem.repositories.*;
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
    private CourseRepo courseRepository;
    private CourseConductionRepo courseConductionRepo;
    private EnrollmentRepo enrollmentRepo;

    public SchoolMSBootstrap(LecturerRepository lecturerRepository, StudentRepository studentRepository,
                             CourseRepo courseRepository, CourseConductionRepo courseConductionRepo) {
        this.lecturerRepository = lecturerRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.courseConductionRepo = courseConductionRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        studentRepository.saveAll(studentListWithEnrollment());
          courseRepository.saveAll(getCoursesWithPrerequisites());
       /* courseConductionRepo.saveAll(getCourseConductions());*/
/*
        log.debug("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nLoading bootstrap data\n\n\n\n\n\n\n\n\n\n\n");
*/

    }


    private List<CourseConduction> getCourseConductions(){
        List<CourseConduction> courseConductions = new ArrayList<>();
        List<Lecturer> lecturers = new ArrayList<>();
        lecturerRepository.findAll().iterator()
                .forEachRemaining(lecturer -> lecturers.add(lecturer));

        List<Course> courses = new ArrayList<>();
        List<Student> studentsList = new ArrayList<>();
        courseRepository.findAll().iterator().forEachRemaining(course -> courses.add(course));







    CourseConduction courseConduction = new CourseConduction();
    courseConduction.setCapacity(60);
    courseConduction.setSemester(Semester.FIRST);
    courseConduction.setYear(Year.of(2014));
   /* courseConduction.addEnrollment(enrollment1);
    courseConduction.addEnrollment(enrollment2);*/
    courseConduction.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId()==1).findFirst().get());
    courseConduction.setCourse(courses.stream().filter(course -> course.getId()==1).findFirst().get());

    courseConductions.add(courseConduction);



        CourseConduction courseConduction1 = new CourseConduction();
        courseConduction1.setCapacity(150);
        courseConduction1.setYear(Year.of(2017));
        courseConduction1.setSemester(Semester.SECOND);
        courseConduction1.setLecturer(lecturers.stream().filter(lecturer -> lecturer.getId()==2).findFirst().get());
        courseConduction1.setCourse(courses.stream().filter(course -> course.getId()==2).findFirst().get());
        /*courseConduction1.addEnrollment(enrollment1);
        courseConduction.addEnrollment(enrollment2);
        courseConduction.addEnrollment(enrollment3);
        courseConduction.addEnrollment(enrollment4);
        courseConduction.addEnrollment(enrollment5);
        courseConduction.addEnrollment(enrollment6);

*/


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
        Course course1 = new Course("Introduction to mathematics", DepartmentalCode.MTH);
        Course course2 = new Course("Introduction to mathematics 2", DepartmentalCode.MTH);
        Course course3 = new Course("Advanced mathematics", DepartmentalCode.MTH);
        Course course4 =  new Course("Basic Chemistry", DepartmentalCode.CHM);
        Course course5 = new Course("Advanced Chemistry", DepartmentalCode.CHM);

        //set prerequisites
    course2.getPrerequisitesCollection().add(course1);
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

private List<Student> studentListWithEnrollment(){
    Enrollment enrollment1 = new Enrollment(new Assessment(40.0,34.5));
    Enrollment enrollment2 = new Enrollment( new Assessment(12.3,42.3));
    Enrollment enrollment3 = new Enrollment(new Assessment(34.3,32.4));
    Enrollment enrollment4 = new Enrollment(new Assessment(98.3,39.3));
    Enrollment enrollment5 = new Enrollment( new Assessment(34.3,43.3));
    Enrollment enrollment6 = new Enrollment(new Assessment(32.3,12.1));

    Student student7 = new Student("eokoli","Okoli","Emeka","e.okoli@gmail.com","Health Sports","password",enrollment1);

    Student student1 = new Student("jhoover","Jonathan","Hoover","j.hoover@fbi.gov.us","Espionage","password",enrollment1);

    Student student2 = new Student("mford","Mark", "Ford","m.ford@mcm.org.au","Computer Science","password",enrollment2);
    Student student3 = new Student("aread","Andrew","Read","a.read@mit.edu.au","Architecture","password",enrollment3);
    Student student4 = new Student("shanity","Shawn","Hannity","s.hannity@foxnews.com","Journalism","password",enrollment4);
    Student student5 = new Student("dtrump","Donald","Trump","d.trump@gmail.com","Mechanical Engineering","password",enrollment5);
    Student student6= new Student("rlarossa","Rebecca","Larossa","r.larossa@thegrange.edu.au","Teaching","password",enrollment6);

    List<Student> studentList = new ArrayList<>(Arrays.asList(student1,student2,student3,student4,student5,student6));
    return studentList;
}

}
