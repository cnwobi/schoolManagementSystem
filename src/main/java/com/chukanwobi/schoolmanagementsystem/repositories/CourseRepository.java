package com.chukanwobi.schoolmanagementsystem.repositories;

import com.chukanwobi.schoolmanagementsystem.models.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course,Long> {
    Optional<Course> findCourseById(Long courseId);
}
