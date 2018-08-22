package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Student implements UserDetails {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Byte[] image;
    private String userName;
    private String firstName;
    private String surname;
    private String email;
    private String major;
    private String password;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student")
    private List<Enrollment> enrollmentList = new ArrayList<>();


    public Student enroll(Enrollment enrollment){
        enrollment.setStudent(this);
        this.enrollmentList.add(enrollment);
        return this;

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
}
