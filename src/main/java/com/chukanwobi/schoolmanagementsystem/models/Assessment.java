package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal assessmentOne;
    private BigDecimal assessmentTwo;

   /* @ManyToMany
    @JoinTable(name = "enrollment_assessment",
    joinColumns = @JoinColumn(name = "assessment_id"),
    inverseJoinColumns = @JoinColumn(name = "enrollment_id"))
    private List<Enrollment> enrollments =  new ArrayList<>();*/
     @OneToOne
    private CourseConduction courseConduction;

}
