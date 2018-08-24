package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;


import java.util.List;

public interface StudentService {
    List<StudentCommand> findStudentsByClassIdAndLecturerId(Long classId, Long lecturerId);
}
