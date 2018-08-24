package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;

import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class CourseConduction {
    public CourseConduction() {
    }

    public CourseConduction(Integer capacity, Lecturer lecturer, Course course) {

        this.capacity = capacity;
        this.lecturer = lecturer;
        this.course = course;
    }





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Semester semester;
    private Integer capacity;
    @ManyToOne
    private Lecturer lecturer;
    @ManyToOne
    private Course course;

    private Year year;




    @OneToMany(mappedBy = "courseConduction",cascade = CascadeType.ALL)
    private List<Enrollment> enrollments = new ArrayList<>();






    public CourseConduction addEnrollment(Enrollment enrollment){
        enrollments.add(enrollment);
        enrollment.setCourseConduction(this);
        return this;
    }

}
