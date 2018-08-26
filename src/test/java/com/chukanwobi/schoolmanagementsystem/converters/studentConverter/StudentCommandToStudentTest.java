package com.chukanwobi.schoolmanagementsystem.converters.studentConverter;

import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters.EnrollmentCommandToEnrollment;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StudentCommandToStudentTest {
    private StudentCommandToStudent studentCommandToStudentConverter;
    private static final Long LONG_ID= 1l;
    private static final String STRING_USERNAME= "user";
    private static final String STRING_PASSWORD = "pass";
    private static final String STRING_EMAIL="email";
    private static final String FIRSTNAME= "first";
    private static final String MAJOR= "some major";
    private static final String SURNAME = "surname";
    private static final List<EnrollmentCommand> ENROLLMENT_COMMAND_LIST= new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        studentCommandToStudentConverter = new StudentCommandToStudent(new EnrollmentCommandToEnrollment());
    }

    @Test
    public void testReturnNullGivenNull(){
        assertNull(studentCommandToStudentConverter.convert(null));
    }

    @Test
    public void testConvert() {
        StudentCommand studentCommand = new StudentCommand();
        studentCommand.setUsername(STRING_USERNAME);
        studentCommand.setEmail(STRING_EMAIL);
        studentCommand.setFirstName(FIRSTNAME);
        studentCommand.setSurname(SURNAME);
        studentCommand.setMajor(MAJOR);
        studentCommand.setPassword(STRING_PASSWORD);
        studentCommand.setId(LONG_ID);
        EnrollmentCommand enrollmentCommand = new EnrollmentCommand();
        EnrollmentCommand enrollmentCommand1 = new EnrollmentCommand();

        ENROLLMENT_COMMAND_LIST.add(enrollmentCommand);
        ENROLLMENT_COMMAND_LIST.add(enrollmentCommand1);
      studentCommand.setEnrollmentList(ENROLLMENT_COMMAND_LIST);

        Student student = studentCommandToStudentConverter.convert(studentCommand);
            assertEquals(LONG_ID,student.getId());
        assertEquals(STRING_USERNAME,student.getUsername());
     assertEquals(FIRSTNAME,student.getFirstName());
    assertEquals(SURNAME,student.getSurname());
    assertEquals(MAJOR,student.getMajor());
    assertEquals(STRING_PASSWORD,student.getPassword());
    assertEquals(STRING_EMAIL,student.getEmail());
    assertEquals(ENROLLMENT_COMMAND_LIST.size(),student.getEnrollments().size());
    }
}