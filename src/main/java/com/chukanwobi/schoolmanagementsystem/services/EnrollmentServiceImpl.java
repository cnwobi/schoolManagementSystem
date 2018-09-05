package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters.EnrollmentToEnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Enrollment;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import com.chukanwobi.schoolmanagementsystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.CurrentSemesterUtil;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
private StudentRepository studentRepository;
@Autowired
    private EnrollmentToEnrollmentCommand enrollmentToEnrollmentCommandConverter;

    private List<EnrollmentCommand> filterEnrollmetnsByCurrentSemesterAndYear(List<EnrollmentCommand> commandList) {
        List<EnrollmentCommand> currentEnrollments = new ArrayList<>();
        commandList.stream().filter(enrollmentCommand-> enrollmentCommand.getCourseConduction().getSemester() == CurrentSemesterUtil.getInstance().calculateCurrentSemester() && enrollmentCommand.getCourseConduction().getYear().toString().equalsIgnoreCase(Year.now().toString())).forEach(enrollmentCommand-> currentEnrollments.add(enrollmentCommand));
        return currentEnrollments;
    }

    private List<EnrollmentCommand> getPastCourseConductionCommands(List<EnrollmentCommand> commandList) {
        List<EnrollmentCommand> pastEnrollments = new ArrayList<>(commandList);

        commandList.stream().filter(enrollmentCommand -> enrollmentCommand.getCourseConduction().getSemester() == CurrentSemesterUtil.getInstance().calculateCurrentSemester() && enrollmentCommand.getCourseConduction().getYear().toString().equalsIgnoreCase(Year.now().toString()))
                .forEach(enrollmentCommand -> pastEnrollments.remove(enrollmentCommand));

        return pastEnrollments;
    }


    @Override
    public List<EnrollmentCommand> findEnrollmentsByStudentId(Long id) {

        List<EnrollmentCommand> enrollments = new ArrayList<>();
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
          optionalStudent.get().getEnrollments().stream().forEach(enrollment -> enrollments.add(enrollmentToEnrollmentCommandConverter.convert(enrollment)));
        }

        return enrollments;
    }

    @Override
    public List<EnrollmentCommand> findPastEnrollmentsByStudentId(Long id) {
        return getPastCourseConductionCommands(findEnrollmentsByStudentId(id));
    }

    @Override
    public List<EnrollmentCommand> findCurrentEnrollmentsByStudentId(Long id) {
        return filterEnrollmetnsByCurrentSemesterAndYear(findEnrollmentsByStudentId(id));
    }
}
