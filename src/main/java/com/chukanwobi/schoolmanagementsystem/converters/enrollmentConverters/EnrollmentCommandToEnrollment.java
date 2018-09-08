package com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters;

import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Enrollment;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentCommandToEnrollment implements Converter<EnrollmentCommand, Enrollment> {

    @Override
    @Synchronized
    @Nullable
    public Enrollment convert(EnrollmentCommand enrollmentCommand) {
        if (enrollmentCommand == null) {
            return null;
        }
         Enrollment enrollment = new Enrollment();
        enrollment.setId(enrollmentCommand.getId());
        enrollment.setCourseConduction(enrollmentCommand.getCourseConduction());
        enrollment.setStudent(enrollmentCommand.getStudent());

        if(enrollmentCommand.getAssessments().size()>0&& enrollmentCommand.getAssessments()!=null){
            enrollmentCommand.getAssessments().forEach(assessment -> enrollment.getAssessments().add(assessment));
        }
   return enrollment ;
    }
}

