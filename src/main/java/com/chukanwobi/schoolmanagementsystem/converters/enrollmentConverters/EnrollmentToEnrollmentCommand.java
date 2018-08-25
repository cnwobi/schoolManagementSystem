package com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters;


import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.assessmentConverters.AssessmentToAssessmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionToCourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.studentConverter.StudentToStudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Enrollment;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentToEnrollmentCommand implements Converter<Enrollment, EnrollmentCommand> {
    @Autowired
    private CourseConductionToCourseConductionCommand courseConverter;
    @Autowired
    private AssessmentToAssessmentCommand assessmentConverter;
    @Autowired
    private StudentToStudentCommand studentConverter;


    public EnrollmentToEnrollmentCommand(CourseConductionToCourseConductionCommand courseConverter, AssessmentToAssessmentCommand assessmentConverter, StudentToStudentCommand studentConverter) {
        this.courseConverter = courseConverter;
        this.assessmentConverter = assessmentConverter;
        this.studentConverter = studentConverter;
    }

 /*   public EnrollmentToEnrollmentCommand() {
    }
*/

    @Override
    @Synchronized
    @Nullable
    public EnrollmentCommand convert(Enrollment enrollment) {
        if (enrollment == null) {
            return null;
        }
        EnrollmentCommand command = new EnrollmentCommand();
        command.setId(enrollment.getId());

        command.setCourseConduction(enrollment.getCourseConduction());

        command.setAssessment(enrollment.getAssessment());


        return command;
    }
}
