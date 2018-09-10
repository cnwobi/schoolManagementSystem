package com.chukanwobi.schoolmanagementsystem.converters.studentConverter;

import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentToStudentCommand implements Converter<Student,StudentCommand> {

    @Override
    public StudentCommand convert(Student student) {
        if(student==null){
            return null;
        }
    StudentCommand command = new StudentCommand();
        command.setId(student.getId());
        command.setUsername(student.getUsername());
        command.setFirstName(student.getFirstName());
        command.setSurname(student.getSurname());
        command.setEmail(student.getEmail());
        command.setMajor(student.getMajor());
        command.setPassword(student.getPassword());
        command.setUsername(student.getUsername());

               return command;
    }
}
