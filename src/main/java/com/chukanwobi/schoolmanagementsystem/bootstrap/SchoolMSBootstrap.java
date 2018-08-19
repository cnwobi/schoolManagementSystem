package com.chukanwobi.schoolmanagementsystem.bootstrap;

import com.chukanwobi.schoolmanagementsystem.models.Course;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseRepository;
import com.chukanwobi.schoolmanagementsystem.repositories.LecturerRepository;
import com.chukanwobi.schoolmanagementsystem.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SchoolMSBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private LecturerRepository lecturerRepository;
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public SchoolMSBootstrap(LecturerRepository lecturerRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.lecturerRepository = lecturerRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        log.debug("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nLoading bootstrap data\n\n\n\n\n\n\n\n\n\n\n");
        lecturerRepository.saveAll(getLecturers());

    }


    private List<Lecturer> getLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        Lecturer lecturer1 = new Lecturer();
        lecturer1.setFirstName("Fabian");
        lecturer1.setSurname("Onyeuka");
        lecturer1.setCampus("FUTO");
        lecturer1.setEmail("f.onyeuka@futo.co.ng");
        lecturers.add(lecturer1);

        Lecturer lecturer2 = new Lecturer();
        lecturer2.setFirstName("Dennis");
        lecturer2.setSurname("Ezechi");
        lecturer2.setCampus("FUTMINNA");
        lecturer2.setEmail("d.ezechi@futminna.co.ng");
        lecturers.add(lecturer2);

        return lecturers;
    }


}
