package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.enrollmentConverters.EnrollmentToEnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.models.Assessment;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Enrollment;
import com.chukanwobi.schoolmanagementsystem.models.Student;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseConductionRepo;
import com.chukanwobi.schoolmanagementsystem.repositories.EnrollmentRepo;
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
    @Autowired
    private CourseConductionRepo courseConductionRepo;
    @Autowired
    private EnrollmentRepo enrollmentRepo;

    private List<EnrollmentCommand> filterEnrollmentsByCurrentSemesterAndYear(List<EnrollmentCommand> commandList) {
        /*Filter any course conduction list passed as argument using the current year and semester...
        * Please see CurrentSemesterUtil for the logic for the current semester*/
        List<EnrollmentCommand> currentEnrollments = new ArrayList<>();
        commandList.stream().filter(enrollmentCommand -> enrollmentCommand.getCourseConduction().getSemester() == CurrentSemesterUtil.getInstance().calculateCurrentSemester() && enrollmentCommand.getCourseConduction().getYear().toString().equalsIgnoreCase(Year.now().toString())).forEach(enrollmentCommand -> currentEnrollments.add(enrollmentCommand));
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
        if (optionalStudent.isPresent()) {
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
        return filterEnrollmentsByCurrentSemesterAndYear(findEnrollmentsByStudentId(id));
    }

    @Override
    public void saveEnrollment(EnrollmentCommand command) {
        Optional<Student> optionalStudent = studentRepository.findById(command.getStudentIdentity());
        Optional<CourseConduction> optionalConduction = courseConductionRepo.findById(command.getCourseConductionId());

        if (!optionalConduction.isPresent()) {
            throw new RuntimeException("Course was not found during enrollment");
        }

        if (!optionalStudent.isPresent()) {
            throw new RuntimeException("Student was not found during enrollment");
        }

        Enrollment enrollment = new Enrollment(new Assessment());

        enrollment.addCourseConduction(optionalConduction.get());
        enrollment.addStudent(optionalStudent.get());

        enrollmentRepo.save(enrollment);


    }
}
