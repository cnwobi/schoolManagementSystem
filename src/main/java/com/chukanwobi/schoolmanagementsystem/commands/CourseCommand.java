package com.chukanwobi.schoolmanagementsystem.commands;

import com.chukanwobi.schoolmanagementsystem.models.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CourseCommand {
    private Long id;
    private String title;
    private DepartmentalCodes departmentalCodes;


}
