package com.chukanwobi.schoolmanagementsystem.commands;

import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class LecturerCommand {
    private Long id;
    private Byte[] image;
    private String userName;
    private String firstName;
    private String surname;
    private String email;
    private String campus;
    private String password;

    private List<CourseConduction> coursesConducted = new ArrayList<>();

}
