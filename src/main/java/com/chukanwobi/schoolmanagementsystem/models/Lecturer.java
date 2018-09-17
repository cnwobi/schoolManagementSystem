package com.chukanwobi.schoolmanagementsystem.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Qualifier("lecturer")
public class Lecturer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Byte[] image;
    private String firstName;
    private String surname;
    private String email;
    private String campus;
    private String password;
    private String role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "lecturer")
    private List<CourseConduction> coursesConducted = new ArrayList<>();

    public Lecturer() {
        role = "LECTURER";
    }

    public Lecturer(String username, String firstName, String surname, String email, String campus, String password, String role) {
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.campus = campus;
        this.password = password;
        this.role = role;
    }

    public Lecturer addCoursesConducted(CourseConduction courseConduction){
        courseConduction.setLecturer(this);
        this.coursesConducted.add(courseConduction);
        return this;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Lecturer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
