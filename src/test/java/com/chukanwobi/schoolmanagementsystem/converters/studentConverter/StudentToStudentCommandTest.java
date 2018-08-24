package com.chukanwobi.schoolmanagementsystem.converters.studentConverter;

import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentToStudentCommandTest {
    StudentToStudentCommand converter;
    private static final Long LONG_ID= 1l;
    private static final String STRING_USERNAME= "user";
    private static final String STRING_PASSWORD = "pass";
    private static final String STRING_EMAIL="email";
    private static final String FIRSTNAME= "first";
    private static final String MAJOR= "some major";
    private static final String SURNAME = "surname";



    @Before
    public void setUp() throws Exception {
        converter = new StudentToStudentCommand();
    }



    @Test
    public void testReturnNullWhenNullParameterIsPassed(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testConvert(){
        Student student = new Student();
        student.setId(LONG_ID);
        student.setUsername(STRING_USERNAME);
        student.setFirstName(FIRSTNAME);
        student.setSurname(SURNAME);
        student.setEmail(STRING_EMAIL);
        student.setMajor(MAJOR);
        student.setPassword(STRING_PASSWORD);

        StudentCommand studentCommand = converter.convert(student);



        assertEquals(LONG_ID,studentCommand.getId());
        assertEquals(FIRSTNAME,studentCommand.getFirstName());
        assertEquals(SURNAME,studentCommand.getSurname());
        assertEquals(STRING_EMAIL,studentCommand.getEmail());
        assertEquals(MAJOR,studentCommand.getMajor());
        assertEquals(STRING_PASSWORD,studentCommand.getPassword());
        assertEquals(STRING_USERNAME,studentCommand.getUsername());
    }
}