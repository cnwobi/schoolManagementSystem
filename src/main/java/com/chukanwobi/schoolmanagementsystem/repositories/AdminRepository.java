package com.chukanwobi.schoolmanagementsystem.repositories;

import com.chukanwobi.schoolmanagementsystem.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminByUsername(String s);
}
