package com.chukanwobi.schoolmanagementsystem.repositories;

import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LecturerRepository extends CrudRepository<Lecturer,Long> {
   Optional<Lecturer> findLecturerByUsername(String username);
}
