package com.chukanwobi.schoolmanagementsystem.converters.studentConverter;

import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StudentCommandToStudentTest {
    private StudentCommandToStudent studentCommandToStudentConverter;
    private static final Long LONG_ID= 1l;
    private static final String STRING_USERNAME= "user";
    private static final String STRING_PASSWORD = "pass";
    private static final String STRING_EMAIL="email";
    private static final String FIRSTNAME= "first";
    private static final String MAJOR= "some major";
    private static final String SURNAME = "surname";


    @Before
    public void setUp() throws Exception {
        studentCommandToStudentConverter = new StudentCommandToStudent();
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


        Student student = studentCommandToStudentConverter.convert(studentCommand);
            assertEquals(LONG_ID,student.getId());
        assertEquals(STRING_USERNAME,student.getUsername());
     assertEquals(FIRSTNAME,student.getFirstName());
    assertEquals(SURNAME,student.getSurname());
    assertEquals(MAJOR,student.getMajor());
    assertEquals(STRING_PASSWORD,student.getPassword());
    assertEquals(STRING_EMAIL,student.getEmail());

    }
}