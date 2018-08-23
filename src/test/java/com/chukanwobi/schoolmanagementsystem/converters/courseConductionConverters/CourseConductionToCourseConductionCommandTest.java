package com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.models.*;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CourseConductionToCourseConductionCommandTest {
    CourseConductionToCourseConductionCommand converter;
    private static  final Long ID = 1l;
    private static final Semester SEMESTER = Semester.FIRST;
    private static final Integer INTEGER_CAPACITY = 150;
    private static final Lecturer LECTURER = new Lecturer();
    private static final Course COURSE= new Course();
    private static final Year YEAR = Year.of(2011);
    private static final List<Enrollment>  ENROLLMENT_LIST = new ArrayList<>();
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

        Enrollment enrollment = new Enrollment();
        enrollment.setId(2l);

        Enrollment enrollment1 = new Enrollment();
        enrollment.setId(3l);

        ENROLLMENT_LIST.add(enrollment);
        ENROLLMENT_LIST.add(enrollment1);
courseConduction.setEnrollmentList(ENROLLMENT_LIST);

        CourseConductionCommand command = converter.convert(courseConduction);

        assertEquals(ID,command.getId());
        assertEquals(YEAR,command.getYear());
        assertEquals(SEMESTER,command.getSemester());
        assertEquals(INTEGER_CAPACITY,command.getCapacity());
        assertEquals(COURSE,command.getCourse());
        assertEquals(LECTURER,command.getLecturer());
       assertEquals(ENROLLMENT_LIST.size(),command.getEnrollments().size());
       assertEquals(ENROLLMENT_LIST.get(0),command.getEnrollments().get(0));



    }
}