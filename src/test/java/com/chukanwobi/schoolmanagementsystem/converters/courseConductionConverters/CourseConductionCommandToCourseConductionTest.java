package com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConverters.CourseCommandToCourse;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerCommandToLecturer;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Semester;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CourseConductionCommandToCourseConductionTest {
    CourseConductionCommandToCourseConduction converter;


    private static final Long ID = 1l;
    private static final Semester SEMESTER = Semester.FIRST;
    private static final Integer INTEGER_CAPACITY = 150;
    private static final LecturerCommand LECTURER = new LecturerCommand();
    private static final CourseCommand COURSE = new CourseCommand();
    private static final Long COURSE_ID= 4l;
    private static final String TITLE= "some title";
    private static final Year YEAR = Year.of(2011);


    @Before
    public void setUp() throws Exception {
converter = new CourseConductionCommandToCourseConduction(new LecturerCommandToLecturer(),new CourseCommandToCourse());

    }
@Test
 public void returnNullWhenGivenNull(){
    assertNull(converter.convert(null));

}
    @Test
    public void testConvert() {
        CourseConductionCommand courseConductionCommand = new CourseConductionCommand();
        courseConductionCommand.setId(ID);

        courseConductionCommand.setYear(YEAR);
        courseConductionCommand.setSemester(SEMESTER);
        courseConductionCommand.setCapacity(INTEGER_CAPACITY);
        COURSE.setId(COURSE_ID);
        COURSE.setTitle(TITLE);
        LECTURER.setId(10L);
        courseConductionCommand.setCourse(COURSE);
        courseConductionCommand.setLecturer(LECTURER);



        CourseConduction courseConduction = converter.convert(courseConductionCommand);

        assertEquals(ID,courseConduction.getId());
        assertEquals(YEAR,courseConduction.getYear());
        assertEquals(LECTURER.getId(),courseConduction.getLecturer().getId());
        assertEquals(COURSE_ID,courseConduction.getCourse().getId());
        assertEquals(SEMESTER,courseConduction.getSemester());
        assertEquals(INTEGER_CAPACITY,courseConduction.getCapacity());

    }
}