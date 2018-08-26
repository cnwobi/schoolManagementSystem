package com.chukanwobi.schoolmanagementsystem.converters.studentConverter;

import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters.EnrollmentCommandToEnrollment;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;

@Component
public class StudentCommandToStudent implements Converter<StudentCommand, Student> {

   private EnrollmentCommandToEnrollment enrollmentCommandToEnrollmentConverter;

    public StudentCommandToStudent(EnrollmentCommandToEnrollment enrollmentCommandToEnrollmentConverter) {
        this.enrollmentCommandToEnrollmentConverter = enrollmentCommandToEnrollmentConverter;
    }

    @Override
    @Synchronized
    @Nullable
    public Student convert(StudentCommand studentCommand) {
        if (studentCommand == null) {
            return null;
        }
    Student student = new Student();
        student.setId(studentCommand.getId());
       student.setFirstName(studentCommand.getFirstName());
    student.setSurname(studentCommand.getSurname());
    student.setEmail(studentCommand.getEmail());
    student.setMajor(studentCommand.getMajor());
    student.setUsername(studentCommand.getUsername());
    student.setPassword(studentCommand.getPassword());

    if(studentCommand.getEnrollmentList()!=null && studentCommand.getEnrollmentList().size()>0){
        studentCommand.getEnrollmentList().forEach(enrollmentCommand -> student.getEnrollments().add(enrollmentCommandToEnrollmentConverter.convert(enrollmentCommand)));
    }
    return student;

    }
}
