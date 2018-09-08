package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CourseConduction courseConduction;
    @ManyToOne
    private Student student;
    @OneToMany(mappedBy = "enrollment",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Assessment> assessments = new ArrayList<>();

    public Enrollment() {
    }

    public Enrollment(Student student) {
        this.student = student;
    }

    public Enrollment(Assessment assessment) {
        addAssessment(assessment);
    }

    public Enrollment(Student student, Assessment assessment) {
        this.student = student;
        addAssessment(assessment);
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setCourseConduction(CourseConduction courseConduction) {
        this.courseConduction = courseConduction;

    }

    public void setStudent(Student student) {
        this.student = student;
    }

   /* public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }*/
  public void addCourseConduction(CourseConduction courseConduction){
        courseConduction.getEnrollments().add(this);
        this.setCourseConduction(courseConduction);

  }

  public void addAssessment(Assessment assessment){
        assessment.setEnrollment(this);
        this.getAssessments().add(assessment);
  }

  public void addStudent(Student student){
        student.getEnrollments().add(this);
        this.setStudent(student);
  }
    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
               /* ", courseConduction=" + courseConduction.getCourse().getTitle() +*/
               /* ", student=" + student.getFirstName() +*/
                '}';
    }

}
