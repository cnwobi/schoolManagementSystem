package com.chukanwobi.schoolmanagementsystem.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@Entity
@EqualsAndHashCode(of="id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(value = EnumType.STRING)

    private DepartmentalCodes departmentalCodes;


@ManyToMany(mappedBy = "prerequisitesCollection")
  private List<Course> isAPrerequisiteOfCollection;


    @ManyToMany()
    @JoinTable(name = "course_prerequisite",joinColumns =  {@JoinColumn(name = "is_a_prerequisite_of_id")},
            inverseJoinColumns =  @JoinColumn(name = "prerequisite_id"))
    private List<Course> prerequisitesCollection = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<CourseConduction> courseConductionList = new ArrayList<>();





    public Course() {
    }

    public Course(String title, DepartmentalCodes departmentalCodes) {
        this.title = title;
        this.departmentalCodes = departmentalCodes;
    }
//Bi directionals


 public Course addCourseConductions(CourseConduction courseConduction){
        courseConduction.setCourse(this);
        this.courseConductionList.add(courseConduction);
        return this;
 }





    protected boolean canEqual(Object other) {
        return other instanceof Course;
    }


}
