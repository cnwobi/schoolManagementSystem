package com.chukanwobi.schoolmanagementsystem.converters.assessmentConverters;

import com.chukanwobi.schoolmanagementsystem.commands.AssessmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters.EnrollmentToEnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Assessment;
import com.chukanwobi.schoolmanagementsystem.models.Enrollment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssessmentToAssessmentCommandTest {
AssessmentToAssessmentCommand assessmentConverter;
EnrollmentToEnrollmentCommand enrollmentConverter;
private final static Long ID = 1l;
private final static Double ASSESSMENT_1 =4.6;
private final static Double ASSESSMENT_2 = 86.9;
private final static Enrollment ENROLLMENT = new Enrollment();
private final static Long ENROLLMENT_ID=4l;

@Before
public void setUp(){
    assessmentConverter = new AssessmentToAssessmentCommand();

}
@Test
public void testReturnNullWhenNullIsPassed(){
    assertNull(assessmentConverter.convert(null));
}
    @Test
    public void testConvert() {
        Assessment assessment = new Assessment();
        assessment.setId(ID);
        assessment.setAssessmentOne(ASSESSMENT_1);
        assessment.setAssessmentTwo(ASSESSMENT_2);
        ENROLLMENT.setId(ENROLLMENT_ID);
        assessment.setEnrollment(ENROLLMENT);


        AssessmentCommand command = assessmentConverter.convert(assessment);

        assertEquals(ID,command.getId());
        assertEquals(ASSESSMENT_1,command.getAssessmentOne());
        assertEquals(ASSESSMENT_2,command.getAssessmentTwo());


    }
}