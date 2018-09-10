package com.chukanwobi.schoolmanagementsystem.converters.studentConverter;

import com.chukanwobi.schoolmanagementsystem.commands.StudentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionToCourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConverters.CourseToCourseCommand;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerToLecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
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
    private static final String LECTURER_1_NAME ="dennis";
    private static final String LECTURER_2_NAME = "chuka";

    private static final Lecturer LECTURER_1 = new Lecturer();
    private static final Lecturer LECTURER_2 = new Lecturer();



    @Before
    public void setUp() throws Exception {
        converter = new StudentToStudentCommand( new CourseConductionToCourseConductionCommand(new LecturerToLecturerCommand(),new CourseToCourseCommand()));
        LECTURER_1.setFirstName(LECTURER_1_NAME);
        LECTURER_2.setFirstName(LECTURER_2_NAME);

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
        CourseConduction courseConduction = new CourseConduction();
        courseConduction.setLecturer(LECTURER_1);

        CourseConduction courseConduction1 = new CourseConduction();
        courseConduction1.setLecturer(LECTURER_2);

        student.getConductionSet().add(courseConduction);
        student.getConductionSet().add(courseConduction1);
        student.getConductionSet().add(new CourseConduction());

        StudentCommand studentCommand = converter.convert(student);



        assertEquals(LONG_ID,studentCommand.getId());
        assertEquals(FIRSTNAME,studentCommand.getFirstName());
        assertEquals(SURNAME,studentCommand.getSurname());
        assertEquals(STRING_EMAIL,studentCommand.getEmail());
        assertEquals(MAJOR,studentCommand.getMajor());
        assertEquals(STRING_PASSWORD,studentCommand.getPassword());
        assertEquals(STRING_USERNAME,studentCommand.getUsername());
        assertEquals(3,studentCommand.getConductionSet().size());
    }
}