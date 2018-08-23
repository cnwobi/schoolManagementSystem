package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class Student implements UserDetails {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Byte[] image;
    private String username;
    private String firstName;
    private String surname;
    private String email;
    private String major;
    private String password;



    @ManyToMany(mappedBy = "enrolledStudents", fetch = FetchType.EAGER)
    private Set<CourseConduction> courseConductions;


    public Student() {
    }

    public Student(String username, String firstName, String surname, String email, String major) {
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.major = major;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Id=" + Id +
                ", image=" + Arrays.toString(image) +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", major='" + major + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
