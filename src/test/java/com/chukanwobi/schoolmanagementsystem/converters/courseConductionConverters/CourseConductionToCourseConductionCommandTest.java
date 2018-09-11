package com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConverters.CourseToCourseCommand;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerToLecturerCommand;
import com.chukanwobi.schoolmanagementsystem.converters.studentConverter.StudentToStudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.time.Year;

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


    @Before
    public void setUp() throws Exception {

        converter = new CourseConductionToCourseConductionCommand(new StudentToStudentCommand(),new LecturerToLecturerCommand(),new CourseToCourseCommand());

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
        Student student = new Student();
        student.setFirstName("chuka");
        Student student1 = new Student();
        student1.setFirstName("james");
        courseConduction.getStudents().add(student);
        courseConduction.getStudents().add(student1);



        CourseConductionCommand command = converter.convert(courseConduction);



        assertEquals(ID, command.getId());
        assertEquals(YEAR, command.getYear());
        assertEquals(SEMESTER, command.getSemester());
        assertEquals(INTEGER_CAPACITY, command.getCapacity());
        assertEquals(COURSE.getId(), command.getCourse().getId());
        assertEquals(LECTURER.getId(), command.getLecturer().getId());
        assertEquals(2,command.getStudentCommands().size());


    }
}