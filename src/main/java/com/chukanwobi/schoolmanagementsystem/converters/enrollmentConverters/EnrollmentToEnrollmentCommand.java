package com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters;


import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Enrollment;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentToEnrollmentCommand implements Converter<Enrollment, EnrollmentCommand> {




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
        command.setStudent(enrollment.getStudent());

        return command;
    }
}
