package com.chukanwobi.schoolmanagementsystem.models;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
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



    @ManyToMany
    @JoinTable(name="enrollments",joinColumns = @JoinColumn(name="course_conduction_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();



    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "courseConduction",cascade = CascadeType.ALL)
    private Set<CourseConductionAssessment>courseConductionAssessments = new HashSet<>();




    public CourseConduction addCourseConductionAssessment(CourseConductionAssessment courseConductionAssessment){
        courseConductionAssessment.setCourseConduction(this);
        courseConductionAssessments.add(courseConductionAssessment);
        return this;
    }

   public CourseConduction addStudent(Student student){
        students.add(student);
        student.getConductionSet().add(this);
        return this;
   }

}
