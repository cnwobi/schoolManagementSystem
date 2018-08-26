package com.chukanwobi.schoolmanagementsystem.converters.courseConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.models.Course;
import com.chukanwobi.schoolmanagementsystem.models.DepartmentalCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseCommandToCourseTest {
private CourseCommandToCourse courseCommandToCourseConverter;
    private static final Long LONG_ID=1l;
    private static final String STRING_TITLE="Some title";
    private  static final DepartmentalCode DEPARTMENTAL_CODES= DepartmentalCode.MTH;
    @Before
    public void setUp() throws Exception {
        courseCommandToCourseConverter = new CourseCommandToCourse();
    }
@Test
public void testReturnVoidGivenVoid(){
        assertNull((courseCommandToCourseConverter.convert(null)));
}
    @Test
    public void testConvert() {
        CourseCommand courseCommand = new CourseCommand();
        courseCommand.setId(LONG_ID);
        courseCommand.setTitle(STRING_TITLE);
        courseCommand.setDepartmentalCode(DEPARTMENTAL_CODES);

        Course course = courseCommandToCourseConverter.convert(courseCommand);

        assertEquals(LONG_ID,course.getId());
        assertEquals(STRING_TITLE,course.getTitle());
        assertEquals(DEPARTMENTAL_CODES,course.getDepartmentalCode());




    }
}