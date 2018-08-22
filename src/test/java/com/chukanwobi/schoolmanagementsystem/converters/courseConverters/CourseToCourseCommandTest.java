package com.chukanwobi.schoolmanagementsystem.converters.courseConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseCommand;
import com.chukanwobi.schoolmanagementsystem.models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CourseToCourseCommandTest {
    private static final Long ID = 1l;
    private static final String STRING_TITLE= "Some title";
    private static final DepartmentalCodes DEPARTMENTAL_CODES= DepartmentalCodes.MTH;
    private static final List<Course> PREQUISITE_COURSE_LIST = new ArrayList<>();
    private static final List<CourseConduction> COURSE_CONDUCTION_LIST = new ArrayList<>();
    private static final List<Enrollment> ENROLLMENT_LIST = new ArrayList<>();
    private static final List<CourseAssessment> COURSE_ASSESSMENT_LIST = new ArrayList<>();

    private CourseToCourseCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CourseToCourseCommand();
    }

    @Test
    public void testReturnNullWhenCourseIsNull(){

        assertNull(converter.convert(null));
    }

    @Test
    public void convert() {
     Course course = new Course();
     course.setTitle(STRING_TITLE);
     course.setId(ID);
     course.setDepartmentalCodes(DEPARTMENTAL_CODES);

     // course assessment List
        CourseAssessment courseAssessment = new CourseAssessment();
        courseAssessment.setId(2l);
        CourseAssessment courseAssessment1 = new CourseAssessment();

        COURSE_ASSESSMENT_LIST.add(courseAssessment);
        COURSE_ASSESSMENT_LIST.add(courseAssessment1);
        course.setCourseAssesments(COURSE_ASSESSMENT_LIST);


        Course pre1 = new Course();
        pre1.setId(5l);
        Course pre2 = new Course();
        pre2.setId(6l);

        PREQUISITE_COURSE_LIST.add(pre1);
        PREQUISITE_COURSE_LIST.add(pre2);
        course.setPrerequisitesCollection(PREQUISITE_COURSE_LIST);

        CourseConduction courseConduction = new CourseConduction();
        courseConduction.setId(2l);
        CourseConduction courseConduction1 = new CourseConduction();
        courseConduction1.setId(3l);

        COURSE_CONDUCTION_LIST.add(courseConduction);
        COURSE_CONDUCTION_LIST.add(courseConduction1);
        course.setCourseConductionList(COURSE_CONDUCTION_LIST);


        Enrollment enrollment = new Enrollment();
        Enrollment enrollment1 = new Enrollment();
        ENROLLMENT_LIST.add(enrollment);
        ENROLLMENT_LIST.add(enrollment1);

        course.setEnrollmentList(ENROLLMENT_LIST);

        CourseCommand command = converter.convert(course);

        assertEquals(ID,command.getId());
        assertEquals(STRING_TITLE,command.getTitle());
        assertEquals(DEPARTMENTAL_CODES,command.getDepartmentalCodes());
        assertEquals(PREQUISITE_COURSE_LIST.get(0),command.getPrerequisitesCollection().get(0));
        assertEquals(PREQUISITE_COURSE_LIST.size(),command.getPrerequisitesCollection().size());

        assertEquals(COURSE_CONDUCTION_LIST.get(0),command.getCourseConductionList().get(0));
        assertEquals(COURSE_CONDUCTION_LIST.size(),command.getCourseConductionList().size());

        assertEquals(ENROLLMENT_LIST.get(0),command.getEnrollmentList().get(0));
        assertEquals(ENROLLMENT_LIST.size(),command.getEnrollmentList().size());


        assertEquals(COURSE_ASSESSMENT_LIST.get(0),command.getCourseAssessmentList().get(0));
        assertEquals(COURSE_ASSESSMENT_LIST.size(),command.getCourseAssessmentList().size());

    }
}