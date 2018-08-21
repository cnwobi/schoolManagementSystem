package com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters;

import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.Course;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LecturerToLecturerCommandTest {
    LecturerToLecturerCommand converter;

    private static final Long ID = 1l;
    private static final String SURNAME = "Doe";
    private static final String FIRST_NAME = "John";
    //private static final Byte[] image;
    private static final String CAMPUS = "Some campus";
    private static final String EMAIL = "j.doe@nobody.com";
    private static final String PASSWORD = "password";
    private static final Long COURSE_CONDUCTION_ID = 1l;
    private static final Long COURSE_CONDUCTION_ID_2 = 2l;


    @Before
    public void setUp() throws Exception {
        converter = new LecturerToLecturerCommand();
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void testObjectIsNotNull(){
        assertNotNull(converter.convert(new Lecturer()));
    }
    @Test
public void testConvert(){
        Lecturer lecturer = new Lecturer();
        lecturer.setId(ID);
        //lecturer.setCampus(CAMPUS);
        lecturer.setEmail(EMAIL);
        lecturer.setFirstName(FIRST_NAME);
        lecturer.setSurname(SURNAME);
        lecturer.setPassword(PASSWORD);

        //course conducted
        Course course = new Course();
        course.setTitle("James Ibori");
        CourseConduction courseConduction = new CourseConduction();
        courseConduction.setCapacity(17);
        courseConduction.setCourse(course);
        courseConduction.setLecturer(lecturer);
        courseConduction.setId(COURSE_CONDUCTION_ID);

        CourseConduction courseConduction1 = new CourseConduction();
        courseConduction1.setCapacity(12);
        courseConduction1.setCourse(course);
        courseConduction1.setLecturer(lecturer);
        courseConduction1.setId(COURSE_CONDUCTION_ID_2);



        lecturer.getCoursesConducted().add(courseConduction);
        lecturer.getCoursesConducted().add(courseConduction1);


        LecturerCommand lecturerCommand = converter.convert(lecturer);
        assertEquals(lecturer.getId(),lecturerCommand.getId());
        assertEquals(CAMPUS,lecturerCommand.getCampus());
        assertEquals(lecturer.getEmail(),lecturerCommand.getEmail());
        assertEquals(lecturer.getFirstName(),lecturerCommand.getFirstName());
        assertEquals(lecturer.getSurname(),lecturerCommand.getSurname());
        assertEquals(lecturer.getPassword(),lecturerCommand.getPassword());
        assertEquals(2,lecturerCommand.getCoursesConducted().size());
        assertEquals(lecturer,lecturerCommand.getCoursesConducted().get(0).getLecturer());
        assertEquals(lecturer,lecturerCommand.getCoursesConducted().get(1).getLecturer());
        assertEquals(COURSE_CONDUCTION_ID,lecturerCommand.getCoursesConducted().get(0).getId());

    }
}