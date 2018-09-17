package com.chukanwobi.schoolmanagementsystem.services.security;

import com.chukanwobi.schoolmanagementsystem.models.Admin;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import com.chukanwobi.schoolmanagementsystem.repositories.AdminRepository;
import com.chukanwobi.schoolmanagementsystem.repositories.LecturerRepository;
import com.chukanwobi.schoolmanagementsystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Qualifier("customUserDetails")
public class UserDetailsServiceImpl implements UserDetailsService {
    private LecturerRepository lecturerRepository;
    private StudentRepository studentRepository;
    private AdminRepository adminRepository;

    public UserDetailsServiceImpl(LecturerRepository lecturerRepository, StudentRepository studentRepository, AdminRepository adminRepository) {
        this.lecturerRepository = lecturerRepository;
        this.studentRepository = studentRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      Optional <Lecturer> optionalLecturer = lecturerRepository.findLecturerByUsername(s);
      Optional<Student> optionalStudent = studentRepository.findStudentByUsername(s);
      Optional<Admin> optionalAdmin = adminRepository.findAdminByUsername(s);

      if(optionalStudent.isPresent()){
          Student student = optionalStudent.get();
          UserDetails userDetails = new User(student.getUsername(),student.getPassword(),true,true,true,true,AuthorityUtils.createAuthorityList("ROLE_STUDENT"));
          return userDetails;
      }
      else if(optionalLecturer.isPresent()) {
          Lecturer lecturer = optionalLecturer.get();
          UserDetails userDetails = new User(lecturer.getUsername(),lecturer.getPassword(),true,true,true,true,AuthorityUtils.createAuthorityList("ROLE_LECTURER"));
          return userDetails;
      }

      else if(optionalAdmin.isPresent()){
          Admin admin =  optionalAdmin.get();
          UserDetails userDetails = new User(admin.getUsername(),admin.getPassword(),admin.isEnabled(),admin.isAccountNonExpired()
          ,admin.isCredentialsNonExpired(),admin.isAccountNonLocked(),admin.getAuthorities());
          return userDetails;
      }

      else {
          throw new UsernameNotFoundException("User name "+ s + " does not exist");
      }

    }
}
