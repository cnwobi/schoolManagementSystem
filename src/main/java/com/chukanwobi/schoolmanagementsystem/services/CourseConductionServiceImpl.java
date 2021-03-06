package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.EnrollmentCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionCommandToCourseConduction;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionToCourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.exceptions.NotFoundException;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseConductionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.CurrentSemesterUtil;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class CourseConductionServiceImpl implements CourseConductionService {
    private CourseConductionRepo courseConductionRepo;
    private CourseConductionToCourseConductionCommand conductionConverter;
    private CourseConductionCommandToCourseConduction commandToCourseConductionConverter;


    private CurrentSemesterUtil currentSemesterUtil;

    {
        currentSemesterUtil = CurrentSemesterUtil.getInstance();
    }

    public CourseConductionServiceImpl(CourseConductionRepo courseConductionRepo, CourseConductionToCourseConductionCommand conductionConverter, CourseConductionCommandToCourseConduction commandToCourseConductionConverter) {
        this.courseConductionRepo = courseConductionRepo;
        this.conductionConverter = conductionConverter;
        this.commandToCourseConductionConverter = commandToCourseConductionConverter;
    }


    private List<CourseConductionCommand> filterCourseConductionByCurrentSemesterAndYear(List<CourseConductionCommand> commandList) {
        List<CourseConductionCommand> currentCourseConductions = new ArrayList<>();
        commandList.stream().filter(conductionCommand -> conductionCommand.getSemester() == currentSemesterUtil.calculateCurrentSemester() && conductionCommand.getYear().toString().equalsIgnoreCase(Year.now().toString())).forEach(conductionCommand -> currentCourseConductions.add(conductionCommand));
        return currentCourseConductions;
    }

    private List<CourseConductionCommand> getPastCourseConductionCommands(List<CourseConductionCommand> commandList) {
        List<CourseConductionCommand> pastCourseConductions = new ArrayList<>(commandList);

        commandList.stream().filter(conductionCommand -> conductionCommand.getSemester() == currentSemesterUtil.calculateCurrentSemester() && conductionCommand.getYear().toString().equalsIgnoreCase(Year.now().toString()))
                .forEach(conductionCommand -> pastCourseConductions.remove(conductionCommand));

        return pastCourseConductions;
    }


    @Override
    public CourseConductionCommand findCourseConductionById(Long classId) {
        Optional<CourseConduction> optionalCourse = courseConductionRepo.findById(classId);
        if (!optionalCourse.isPresent()) {
            throw new NotFoundException("Course Conduction (lecturer class) with Id: " + classId + " was not found");
        }

        return conductionConverter.convert(optionalCourse.get());
    }

    @Override
    public List<CourseConductionCommand> findCourseConductionByLecturerId(Long lecturerId) {
        List<CourseConductionCommand> conductionCommandList = new ArrayList<>();

        courseConductionRepo.findAll().iterator().forEachRemaining(courseConduction -> conductionCommandList.add(conductionConverter.convert(courseConduction)));

        log.debug("\n\n\n\n\n\n\n\n\n" + conductionCommandList.toString());
        List<CourseConductionCommand> conductionCommandList1 = new ArrayList<>();
        CourseConductionCommand courseConduction = conductionCommandList.stream().findFirst().get();
        log.debug(courseConduction.toString() + "\n\n\n\n\n\n\n\n\n\n\n\n\n In list conduction");

        conductionCommandList.stream()
                .filter(courseConductionCommand -> courseConductionCommand.getLecturer().getId() == lecturerId)
                .forEach(courseConductionCommand -> conductionCommandList1.add(courseConductionCommand));
        return conductionCommandList1;
    }


    @Override
    public CourseConductionCommand findCourseConductionByIdAndLecturerId(Long id, Long lecturerId) {
        Optional<CourseConduction> optionalCourseConduction = courseConductionRepo.findById(Long.valueOf(id));

        if (!optionalCourseConduction.isPresent()) {
            throw new RuntimeException("Class with Id: " + id + " was not found");
        }

        if (optionalCourseConduction.get().getLecturer().getId() != lecturerId) {
            throw new RuntimeException("You do not teach this class");
        }


        return conductionConverter.convert(optionalCourseConduction.get());
    }

    @Override
    public CourseConductionCommand saveCourseConductionCommand(CourseConductionCommand conductionCommand) {
        CourseConduction unsavedCourseConduction = commandToCourseConductionConverter.convert(conductionCommand);
        CourseConduction savedCourseConduction = courseConductionRepo.save(unsavedCourseConduction);
        log.debug("Saved courseConduction:" + savedCourseConduction.getId());

        return conductionConverter.convert(savedCourseConduction);
    }

    @Override
    public CourseConductionCommand editCapacityAndSave(CourseConductionCommand conductionCommand) {


        CourseConductionCommand courseConductionCommand = findCourseConductionByIdAndLecturerId(conductionCommand.getId(), conductionCommand.getLecturer().getId());
        courseConductionCommand.setCapacity(conductionCommand.getCapacity());


        return saveCourseConductionCommand(courseConductionCommand);

    }


    @Override
    public List<CourseConductionCommand> findCurrentCourseConductionByLecturerId(Long lecturerId) {
        List<CourseConductionCommand> commandList = findCourseConductionByLecturerId(Long.valueOf(lecturerId));
        return filterCourseConductionByCurrentSemesterAndYear(commandList);
    }

    @Override
    public List<CourseConductionCommand> findPastCoursesByLecturerId(Long lecturerId) {
        List<CourseConductionCommand> commandList = findCourseConductionByLecturerId(Long.valueOf(lecturerId));
        return getPastCourseConductionCommands(commandList);
    }


    @Override
    public List<CourseConductionCommand> findAll() {
        List<CourseConductionCommand> commandList = new ArrayList<>();
        courseConductionRepo.findAll().iterator().forEachRemaining(courseConduction -> commandList.add(conductionConverter.convert(courseConduction)));
        return commandList;
    }

    @Override
    public List<CourseConductionCommand> findAllCurrentCourses() {
        return filterCourseConductionByCurrentSemesterAndYear(findAll());
    }

    @Override
    public void enrollStudent(EnrollmentCommand command) {
        EnrollmentCommand loga = command;
log.debug(loga.toString());
    }
}
