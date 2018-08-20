package com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.models.Course;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.models.Semester;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.*;

public class CourseConductionToCourseConductionCommandTest {
    CourseConductionToCourseConductionCommand converter;
    private static  final Long ID = 1l;
    private static final Semester SEMESTER = Semester.FIRST;
    private static final Integer INTEGER_CAPACITY = 150;
    private static final Lecturer LECTURER = new Lecturer();
    private static final Course COURSE= new Course();
    private static final Year YEAR = Year.of(2011);

    @Before
    public void setUp() throws Exception {
   converter = new CourseConductionToCourseConductionCommand();

    }

    @Test
    public void testconvert() {
        CourseConduction courseConduction = new CourseConduction();
        courseConduction.setId(ID);

        courseConduction.setYear(YEAR);
        courseConduction.setSemester(SEMESTER);
        courseConduction.setCapacity(INTEGER_CAPACITY);
        courseConduction.setCourse(COURSE);
        courseConduction.setLecturer(LECTURER);


        CourseConductionCommand command = converter.convert(courseConduction);

        assertEquals(ID,command.getId());
        assertEquals(YEAR,command.getYear());
        assertEquals(SEMESTER,command.getSemester());
        assertEquals(INTEGER_CAPACITY,command.getCapacity());
        assertEquals(COURSE,command.getCourse());
        assertEquals(LECTURER,command.getLecturer());




    }
}