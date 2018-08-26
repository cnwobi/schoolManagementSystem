package com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters;

import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LecturerCommandToLecturerTest {
LecturerCommandToLecturer lecturerCommandToLecturerConverter;
    private static final Long ID = 1l;

    private static final String SURNAME = "Doe";
    private static final String FIRST_NAME = "John";
    //private static final Byte[] image;
    private static final String CAMPUS = "Some campus";
    private static final String EMAIL = "j.doe@nobody.com";
    private static final String PASSWORD = "password";
    private static final Long COURSE_CONDUCTION_ID = 1l;
    private static final Long COURSE_CONDUCTION_ID_2 = 2l;
    private static final String USERNAME = "d.john";

    @Before
    public void setUp() throws Exception {
        lecturerCommandToLecturerConverter= new LecturerCommandToLecturer();
    }
@Test
public void returnNulWhenGivenNull(){
        assertNull(lecturerCommandToLecturerConverter.convert(null));
}
    @Test
    public void convert() {
        LecturerCommand lecturerCommand = new LecturerCommand();
        lecturerCommand.setId(ID);
        lecturerCommand.setFirstName(FIRST_NAME);
        lecturerCommand.setUserName(USERNAME);
        lecturerCommand.setSurname(SURNAME);
        lecturerCommand.setEmail(EMAIL);
        lecturerCommand.setCampus(CAMPUS);
        lecturerCommand.setPassword(PASSWORD);
        CourseConduction courseConduction = new CourseConduction();
        CourseConduction courseConduction1 = new CourseConduction();
        courseConduction.setId(COURSE_CONDUCTION_ID);

        courseConduction1.setId(COURSE_CONDUCTION_ID_2);

        lecturerCommand.getCoursesConducted().add(courseConduction);
        lecturerCommand.getCoursesConducted().add(courseConduction1);

        Lecturer lecturer = lecturerCommandToLecturerConverter.convert(lecturerCommand);


        assertEquals(lecturer.getId(),lecturer.getId());
        assertEquals(CAMPUS,lecturer.getCampus());
        assertEquals(EMAIL,lecturer.getEmail());
        assertEquals(FIRST_NAME,lecturer.getFirstName());
        assertEquals(SURNAME,lecturer.getSurname());
        assertEquals(PASSWORD,lecturer.getPassword());
        assertEquals(2,lecturerCommand.getCoursesConducted().size());

        assertEquals(COURSE_CONDUCTION_ID,lecturer.getCoursesConducted().get(0).getId());
        assertEquals(USERNAME,lecturer.getUsername());
    }
}