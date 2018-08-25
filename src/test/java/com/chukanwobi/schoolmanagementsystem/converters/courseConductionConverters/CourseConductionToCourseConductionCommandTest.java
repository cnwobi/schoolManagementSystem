package com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConverters.CourseToCourseCommand;
import com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters.EnrollmentToEnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerToLecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CourseConductionToCourseConductionCommandTest {

    CourseConductionToCourseConductionCommand converter;
    @Mock
    EnrollmentToEnrollmentCommand enrollmentCommandConverter;
    @Mock
    LecturerToLecturerCommand lecturerConverter;
    @Mock
    CourseToCourseCommand courseConverter;

    private static final Long ID = 1l;
    private static final Semester SEMESTER = Semester.FIRST;
    private static final Integer INTEGER_CAPACITY = 150;
    private static final Lecturer LECTURER = new Lecturer();
    private static final Course COURSE = new Course();
    private static final Year YEAR = Year.of(2011);
    private static final List<Enrollment> ENROLLMENTS = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        converter = new CourseConductionToCourseConductionCommand(enrollmentCommandConverter,lecturerConverter,courseConverter);

    }

    @Test
    public void testConvert() {
        CourseConduction courseConduction = new CourseConduction();
        courseConduction.setId(ID);

        courseConduction.setYear(YEAR);
        courseConduction.setSemester(SEMESTER);
        courseConduction.setCapacity(INTEGER_CAPACITY);
        courseConduction.setCourse(COURSE);
        courseConduction.setLecturer(LECTURER);


        Enrollment enrollment = new Enrollment();
        enrollment.setId(1l);
        Enrollment enrollment1 = new Enrollment();
        enrollment1.setId(2l);
        ENROLLMENTS.add(enrollment);
        ENROLLMENTS.add(enrollment1);


        courseConduction.setEnrollments(ENROLLMENTS);

        CourseConductionCommand command = converter.convert(courseConduction);

        assertEquals(ID, command.getId());
        assertEquals(YEAR, command.getYear());
        assertEquals(SEMESTER, command.getSemester());
        assertEquals(INTEGER_CAPACITY, command.getCapacity());
        assertEquals(COURSE, command.getCourse());
        assertEquals(LECTURER, command.getLecturer());
        assertEquals(ENROLLMENTS.size(), command.getEnrollments().size());


    }
}