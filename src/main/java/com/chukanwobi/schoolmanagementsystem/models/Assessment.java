package com.chukanwobi.schoolmanagementsystem.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double assessmentOne;

    private Double assessmentTwo;

    @Lob
    private String feedback1;
    @Lob
    private String feedback2;

    @OneToOne
    private Enrollment enrollment;

    public Assessment() {
    }

    public Assessment(Double assessmentOne, Double assessmentTwo) {
        if (assessmentOne > 100 || assessmentTwo > 100) {
            throw new RuntimeException("Assessment one or two is more than 100%");
        }
        this.assessmentOne = assessmentOne;
        this.assessmentTwo = assessmentTwo;
    }


}
