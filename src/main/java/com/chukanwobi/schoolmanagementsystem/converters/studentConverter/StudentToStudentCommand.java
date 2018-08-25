package com.chukanwobi.schoolmanagementsystem.converters.studentConverter;

import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters.EnrollmentToEnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentToStudentCommand implements Converter<Student,StudentCommand> {
    @Autowired
private EnrollmentToEnrollmentCommand enrollmentConverter;
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

        if(student.getEnrollments()!=null && student.getEnrollments().size()>0){
            student.getEnrollments().forEach(enrollment -> command.getEnrollmentList().add(enrollmentConverter.convert(enrollment)));
        }
        return command;
    }
}
