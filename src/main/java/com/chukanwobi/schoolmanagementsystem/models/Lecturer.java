package com.chukanwobi.schoolmanagementsystem.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Byte[] image;
    private String firstName;
    private String surname;
    private String email;
    private String campus;
    private String password;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "lecturer")
    private List<CourseConduction> coursesConducted = new ArrayList<>();

    public Lecturer() {
    }


    public Lecturer addCoursesConducted(CourseConduction courseConduction){
        courseConduction.setLecturer(this);
        this.coursesConducted.add(courseConduction);
        return this;
    }

    public Long getId() {
        return this.id;
    }

    public Byte[] getImage() {
        return this.image;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCampus() {
        return this.campus;
    }

    public String getPassword() {
        return this.password;
    }

    public List<CourseConduction> getCoursesConducted() {
        return this.coursesConducted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCoursesConducted(List<CourseConduction> coursesConducted) {
        this.coursesConducted = coursesConducted;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Lecturer)) return false;
        final Lecturer other = (Lecturer) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        if (!java.util.Arrays.deepEquals(this.getImage(), other.getImage())) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$surname = this.getSurname();
        final Object other$surname = other.getSurname();
        if (this$surname == null ? other$surname != null : !this$surname.equals(other$surname)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$campus = this.getCampus();
        final Object other$campus = other.getCampus();
        if (this$campus == null ? other$campus != null : !this$campus.equals(other$campus)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$coursesConducted = this.getCoursesConducted();
        final Object other$coursesConducted = other.getCoursesConducted();
        if (this$coursesConducted == null ? other$coursesConducted != null : !this$coursesConducted.equals(other$coursesConducted))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getImage());
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $surname = this.getSurname();
        result = result * PRIME + ($surname == null ? 43 : $surname.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $campus = this.getCampus();
        result = result * PRIME + ($campus == null ? 43 : $campus.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $coursesConducted = this.getCoursesConducted();
        result = result * PRIME + ($coursesConducted == null ? 43 : $coursesConducted.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Lecturer;
    }


}
