package com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters;

import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Assessment;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Enrollment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnrollmentToEnrollmentCommandTest {
        private EnrollmentToEnrollmentCommand enrollmentCommandConverter;
private final static Long ID=2l;
private final static Long ID_COURSE= 4l;
private final static Long ID_ASSESSMENT= 5l;
private final static CourseConduction COURSE_CONDUCTION= new CourseConduction();
private final static Assessment ENROLLMENT = new Assessment();
    @Before
    public void setUp() throws Exception {

enrollmentCommandConverter=new EnrollmentToEnrollmentCommand();

    }

    @Test
    public void convert() {
 Enrollment enrollment = new Enrollment();
 enrollment.setId(ID);
        COURSE_CONDUCTION.setId(ID_COURSE);
        ENROLLMENT.setId(ID_ASSESSMENT);
 enrollment.setCourseConduction(COURSE_CONDUCTION);
 enrollment.setAssessment(ENROLLMENT);
        EnrollmentCommand enrollmentCommand = enrollmentCommandConverter.convert(enrollment);

        assertEquals(ID,enrollmentCommand.getId());
        assertEquals(ID_COURSE,enrollmentCommand.getCourseConduction().getId());
assertEquals(ID_ASSESSMENT,enrollmentCommand.getAssessment().getId());

    }
}