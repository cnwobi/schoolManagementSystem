package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "perquisite_of")
    private Course theCourse;

    @OneToMany(mappedBy = "theCourse")
    private Set<Course> prerequisites =  new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<CourseConduction> courseConductions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<Enrollment> enrollmentList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<CourseAssessment> courseAssesments = new ArrayList<>();

    //Bi directionals

    public Course setDependentCourse(Course course){
        course.setTheCourse(this);
        this.prerequisites.add(course);
        return this;
    }
 public Course addCourseConductions(CourseConduction courseConduction){
        courseConduction.setCourse(this);
        this.courseConductions.add(courseConduction);
        return this;
 }

 public Course addEnrollment(Enrollment enrollment){
        enrollment.setCourse(this);
        this.enrollmentList.add(enrollment);
        return this;
 }

 public Course addCourseAssements(CourseAssessment courseAssessment){
        courseAssessment.setCourse(this);
        this.courseAssesments.add(courseAssessment);
        return this;
    }
}