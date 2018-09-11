package com.chukanwobi.schoolmanagementsystem.commands;

import com.chukanwobi.schoolmanagementsystem.models.AssessmentRecord;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Calendar;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@EqualsAndHashCode
public class CourseConductionAssessmentCommand {


    private Long id;

    private CourseConductionCommand courseConductionCommand;

    private Set<AssessmentRecord> assessmentRecords =  new HashSet<>();

@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar openDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar DueDate;

    private String title;
    private BigDecimal totalAchievableMarks;

}
