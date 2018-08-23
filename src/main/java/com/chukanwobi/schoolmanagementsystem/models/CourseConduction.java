package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;

import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

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


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "courseConduction")
    private List<Enrollment> enrollmentList = new ArrayList<>();


    @OneToOne
    private Assessment assessment;


    public CourseConduction addEnrollment(Enrollment enrollment){
        enrollment.setCourseConduction(this);
        this.enrollmentList.add(enrollment);
        return this;
    }


}
