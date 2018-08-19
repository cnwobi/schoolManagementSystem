package com.chukanwobi.schoolmanagementsystem.repositories;

import com.chukanwobi.schoolmanagementsystem.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {

}
