package com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConverters.CourseToCourseCommand;
import com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters.EnrollmentToEnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerToLecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CourseConductionToCourseConductionCommandTest {

    CourseConductionToCourseConductionCommand converter;


    private static final Long ID = 1l;
    private static final Semester SEMESTER = Semester.FIRST;
    private static final Integer INTEGER_CAPACITY = 150;
    private static final Lecturer LECTURER = new Lecturer();
    private static final Course COURSE = new Course();
    private static final Long COURSE_ID= 4l;
    private static final String TITLE= "some title";
    private static final Year YEAR = Year.of(2011);
    private static final List<Enrollment> ENROLLMENTS = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        converter = new CourseConductionToCourseConductionCommand(new EnrollmentToEnrollmentCommand(),new LecturerToLecturerCommand(),new CourseToCourseCommand());

    }

    @Test
    public void testConvert() {
        CourseConduction courseConduction = new CourseConduction();
        courseConduction.setId(ID);

        courseConduction.setYear(YEAR);
        courseConduction.setSemester(SEMESTER);
        courseConduction.setCapacity(INTEGER_CAPACITY);
        COURSE.setId(COURSE_ID);
        COURSE.setTitle(TITLE);
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
        assertEquals(COURSE.getId(), command.getCourse().getId());
        assertEquals(LECTURER.getId(), command.getLecturer().getId());
        assertEquals(ENROLLMENTS.size(), command.getEnrollments().size());


    }
}