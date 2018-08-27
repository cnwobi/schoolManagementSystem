package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private CourseConduction courseConduction;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Student student;
    @OneToOne(cascade = CascadeType.ALL)
    private Assessment assessment;

    public Enrollment() {
    }

    public Enrollment(Student student) {
        this.student = student;
    }

    public Enrollment(Assessment assessment) {
        this.assessment = assessment;
    }

    public Enrollment(Student student, Assessment assessment) {
        this.student = student;
        this.assessment = assessment;
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

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }
  public void addCourseConduction(CourseConduction courseConduction){
        courseConduction.getEnrollments().add(this);
        this.setCourseConduction(courseConduction);

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
