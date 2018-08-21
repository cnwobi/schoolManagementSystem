package com.chukanwobi.schoolmanagementsystem.repositories;

import com.chukanwobi.schoolmanagementsystem.models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student,Long> {
Optional<Student>  findStudentByUserName(String s);
}
