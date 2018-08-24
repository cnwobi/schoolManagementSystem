package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {


    @Override
    public List<StudentCommand> findStudentsByClassIdAndLecturerId(Long classId, Long lecturerId) {
        return null;
    }
}
