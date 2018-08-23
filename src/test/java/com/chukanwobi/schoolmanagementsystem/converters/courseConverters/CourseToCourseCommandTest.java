package com.chukanwobi.schoolmanagementsystem.converters.courseConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.models.Course;
import com.chukanwobi.schoolmanagementsystem.models.DepartmentalCodes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseToCourseCommandTest {
    private CourseToCourseCommand converter;
    private static final Long LONG_ID=1l;
    private static final String STRING_TITLE="Some title";
    private  static final DepartmentalCodes DEPARTMENTAL_CODES= DepartmentalCodes.MTH;
    @Before
    public void setUp() throws Exception {
        converter = new CourseToCourseCommand();
    }

    @Test
    public void testReturnNullWhenNullIsPassedIn(){
        assertNull(converter.convert(null));
    }


    @Test
    public void testConvert() {
        Course course = new Course();
        course.setId(LONG_ID);
        course.setDepartmentalCodes(DEPARTMENTAL_CODES);
        course.setTitle(STRING_TITLE);

    CourseCommand command = converter.convert(course);
        assertEquals(LONG_ID,command.getId());
        assertEquals(DEPARTMENTAL_CODES,command.getDepartmentalCodes());
        assertEquals(STRING_TITLE,command.getTitle());
    }
}