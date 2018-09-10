package com.chukanwobi.schoolmanagementsystem.converters.courseConductionAssessmentConverters;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionAssessmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionToCourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConverters.CourseToCourseCommand;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerToLecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.AssessmentRecord;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.CourseConductionAssessment;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class CourseConductionAssessmentToCourseConductionAssessmentCommandTest {
CourseConductionAssessmentToCourseConductionAssessmentCommand converter;
private static final Long ID = 2l;

private static final Calendar OPEN_DATE= new GregorianCalendar(2012,12,11);
private static final Calendar DUE_DATE = new GregorianCalendar(2012,12,30);
private static final String TITLE = "Some title";
private static final Long ID_1_ASS = 2l;
    private static final Long ID_2_ASS = 3l;
    private static final Long ID_1_COURSE_CONDUCTION = 4l;
private static final Double TOTAL_ACHIEVABLE_MARKS = 35.0;

private static final AssessmentRecord ASSESSMENT_RECORD_1 = new AssessmentRecord();

private static final AssessmentRecord ASSESSMENT_RECORD_2 = new AssessmentRecord();
private static  final CourseConduction COURSE_CONDUCTION =  new CourseConduction();
    @Before
    public void setUp() throws Exception {
        ASSESSMENT_RECORD_1.setId(ID_1_ASS);
        ASSESSMENT_RECORD_2.setId(ID_2_ASS);
        COURSE_CONDUCTION.setId(ID_1_COURSE_CONDUCTION);
converter = new CourseConductionAssessmentToCourseConductionAssessmentCommand(new CourseConductionToCourseConductionCommand(new LecturerToLecturerCommand(),new CourseToCourseCommand()));
    }
@Test
    public void returnNullWhenGivenNull(){
        assertNull(converter.convert(null));
    }

    @Test
    public void convert() {
     CourseConductionAssessment assessment = new CourseConductionAssessment();
     assessment.setId(ID);
     assessment.setOpenDate(OPEN_DATE);
     assessment.setDueDate(DUE_DATE);
     assessment.setTitle(TITLE);
     assessment.setTotalAchievableMarks(TOTAL_ACHIEVABLE_MARKS);
    assessment.setCourseConduction(COURSE_CONDUCTION);
     assessment.getAssessmentRecords().add(ASSESSMENT_RECORD_1);
     assessment.getAssessmentRecords().add(ASSESSMENT_RECORD_2);

        CourseConductionAssessmentCommand  command = converter.convert(assessment);

        assertEquals(ID, command.getId());
        assertEquals(OPEN_DATE,command.getOpenDate());
        assertEquals(DUE_DATE,command.getDueDate());
        assertEquals(TITLE,command.getTitle());
        assertEquals(2,command.getAssessmentRecords().size());
        assertEquals(ID_1_COURSE_CONDUCTION,command.getCourseConductionCommand().getId());
        assertEquals(TOTAL_ACHIEVABLE_MARKS,command.getTotalAchievableMarks());
    }
}