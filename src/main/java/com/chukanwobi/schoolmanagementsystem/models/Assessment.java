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
    private String title;

    @ManyToOne
    private Enrollment enrollment;
    private String feedback;
    private Double totalAchievableMarks;
    private Double obtainedMarks ;


    public Assessment() {
    }

    public Assessment(String title, Double totalAchievableMarks, Double obtainedMs) {
        this.title = title;
        this.totalAchievableMarks = totalAchievableMarks;
        this.obtainedMarks = obtainedMarks;
    }

    public String getPercentage(){
        if(obtainedMarks==null){
            return "N/A";
        }
        return obtainedMarks/totalAchievableMarks * 100 + "%";
    }


}
