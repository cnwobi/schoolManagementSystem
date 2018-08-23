package com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.models.*;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CourseConductionToCourseConductionCommandTest {
    CourseConductionToCourseConductionCommand converter;
    private static  final Long ID = 1l;
    private static final Semester SEMESTER = Semester.FIRST;
    private static final Integer INTEGER_CAPACITY = 150;
    private static final Lecturer LECTURER = new Lecturer();
    private static final Course COURSE= new Course();
    private static final Year YEAR = Year.of(2011);
    private static final Set<Student> ENROLLED_STUDENTS = new HashSet<>();
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


  Student student = new Student();
  student.setId(1l);

  Student student1 = new Student();
  student1.setId(2l);


        ENROLLED_STUDENTS.add(student);

        ENROLLED_STUDENTS.add(student1);
courseConduction.setEnrolledStudents(ENROLLED_STUDENTS);

        CourseConductionCommand command = converter.convert(courseConduction);

        assertEquals(ID,command.getId());
        assertEquals(YEAR,command.getYear());
        assertEquals(SEMESTER,command.getSemester());
        assertEquals(INTEGER_CAPACITY,command.getCapacity());
        assertEquals(COURSE,command.getCourse());
        assertEquals(LECTURER,command.getLecturer());
       assertEquals(ENROLLED_STUDENTS.size(),command.getEnrolledStudents().size());




    }
}