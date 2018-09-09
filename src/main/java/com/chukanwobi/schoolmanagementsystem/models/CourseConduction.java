package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity

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



    @OneToMany(mappedBy = "courseConduction",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Enrollment> enrollments = new ArrayList<>();


@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "courseConduction",cascade = CascadeType.ALL)
    private List<CourseConductionAssessment>courseConductionAssessments = new ArrayList<>();



    public CourseConduction addEnrollment(Enrollment enrollment){
        enrollments.add(enrollment);
        enrollment.setCourseConduction(this);
        return this;
    }

    public CourseConduction addCourseConductionAssessment(CourseConductionAssessment courseConductionAssessment){
        courseConductionAssessment.setCourseConduction(this);
        courseConductionAssessments.add(courseConductionAssessment);
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CourseConduction)) return false;
        final CourseConduction other = (CourseConduction) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$semester = this.getSemester();
        final Object other$semester = other.getSemester();
        if (this$semester == null ? other$semester != null : !this$semester.equals(other$semester)) return false;
        final Object this$capacity = this.getCapacity();
        final Object other$capacity = other.getCapacity();
        if (this$capacity == null ? other$capacity != null : !this$capacity.equals(other$capacity)) return false;
        final Object this$lecturer = this.getLecturer();
        final Object other$lecturer = other.getLecturer();
        if (this$lecturer == null ? other$lecturer != null : !this$lecturer.equals(other$lecturer)) return false;
        final Object this$course = this.getCourse();
        final Object other$course = other.getCourse();
        if (this$course == null ? other$course != null : !this$course.equals(other$course)) return false;
        final Object this$year = this.getYear();
        final Object other$year = other.getYear();
        if (this$year == null ? other$year != null : !this$year.equals(other$year)) return false;
        final Object this$enrollments = this.getEnrollments();
        final Object other$enrollments = other.getEnrollments();
        if (this$enrollments == null ? other$enrollments != null : !this$enrollments.equals(other$enrollments))
            return false;
        final Object this$courseConductionAssessments = this.getCourseConductionAssessments();
        final Object other$courseConductionAssessments = other.getCourseConductionAssessments();
        if (this$courseConductionAssessments == null ? other$courseConductionAssessments != null : !this$courseConductionAssessments.equals(other$courseConductionAssessments))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $semester = this.getSemester();
        result = result * PRIME + ($semester == null ? 43 : $semester.hashCode());
        final Object $capacity = this.getCapacity();
        result = result * PRIME + ($capacity == null ? 43 : $capacity.hashCode());
        final Object $lecturer = this.getLecturer();
        result = result * PRIME + ($lecturer == null ? 43 : $lecturer.hashCode());
        final Object $course = this.getCourse();
        result = result * PRIME + ($course == null ? 43 : $course.hashCode());
        final Object $year = this.getYear();
        result = result * PRIME + ($year == null ? 43 : $year.hashCode());
        final Object $enrollments = this.getEnrollments();
        result = result * PRIME + ($enrollments == null ? 43 : $enrollments.hashCode());
        final Object $courseConductionAssessments = this.getCourseConductionAssessments();
        result = result * PRIME + ($courseConductionAssessments == null ? 43 : $courseConductionAssessments.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof CourseConduction;
    }


}
