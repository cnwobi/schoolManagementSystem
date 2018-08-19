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
}
