package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters.EnrollmentToEnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Enrollment;
import com.chukanwobi.schoolmanagementsystem.repositories.EnrollmentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    EnrollmentRepo enrollmentRepo;


    EnrollmentToEnrollmentCommand enrollmentConverter;

    public EnrollmentServiceImpl(EnrollmentRepo enrollmentRepo, EnrollmentToEnrollmentCommand enrollmentConverter) {
        this.enrollmentRepo = enrollmentRepo;
        this.enrollmentConverter = enrollmentConverter;
    }

    @Override
    public List<EnrollmentCommand> findEnrollmentsByClassId(Long classId) {


        List<EnrollmentCommand> enrollmentCommands = new ArrayList<>();
enrollmentRepo.findAll().stream().filter(enrollment -> enrollment.getId()==classId).forEach(enrollment -> enrollmentCommands.add(enrollmentConverter.convert(enrollment)));
log.debug("\n\n\n\n\n in enrollment comment "+ enrollmentCommands.toString());

        return enrollmentCommands;
    }
}
