package com.chukanwobi.schoolmanagementsystem.commands;

import com.chukanwobi.schoolmanagementsystem.models.*;
import lombok.Data;

@Data
public class CourseCommand {
    private Long id;
    private String title;
    private DepartmentalCode departmentalCode;


}
