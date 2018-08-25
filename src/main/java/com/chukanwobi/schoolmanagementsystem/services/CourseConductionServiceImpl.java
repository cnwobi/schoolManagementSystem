package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionToCourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.exceptions.NotFoundException;
import com.chukanwobi.schoolmanagementsystem.models.Course;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseConductionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class CourseConductionServiceImpl implements CourseConductionService{
    private CourseConductionRepo courseConductionRepo;
    private CourseConductionToCourseConductionCommand conductionConverter;

    public CourseConductionServiceImpl(CourseConductionRepo courseConductionRepo, CourseConductionToCourseConductionCommand conductionConverter) {
        this.courseConductionRepo = courseConductionRepo;
        this.conductionConverter = conductionConverter;
    }


    @Override
    public CourseConductionCommand findCourseConductionById(Long classId) {
        Optional<CourseConduction> optionalCourse = courseConductionRepo.findById(classId);
        if(!optionalCourse.isPresent()){
            throw new NotFoundException("Course Conduction (lecturer class) with Id: " +classId+" was not found");
        }

        return conductionConverter.convert(optionalCourse.get());
    }

    @Override
    public List<CourseConductionCommand> findCourseConductionByLecturerId(Long lecturerId) {
        List<CourseConductionCommand> conductionCommandList = new ArrayList<>();

        courseConductionRepo.findAll().iterator().forEachRemaining(courseConduction -> conductionCommandList.add(conductionConverter.convert(courseConduction)));

        log.debug("\n\n\n\n\n\n\n\n\n"+conductionCommandList.toString());
        List<CourseConductionCommand> conductionCommandList1= new ArrayList<>();
        CourseConductionCommand courseConduction = conductionCommandList.stream().findFirst().get();
        log.debug(courseConduction.toString()+"\n\n\n\n\n\n\n\n\n\n\n\n\n In list conduction");

        conductionCommandList.stream()
                .filter(courseConductionCommand -> courseConductionCommand.getLecturer().getId()==lecturerId)
                .forEach(courseConductionCommand ->conductionCommandList1.add(courseConductionCommand) );
        return conductionCommandList1;
    }


    @Override
    public CourseConductionCommand findCourseConductionByIdAndLecturerId(Long id,Long lecturerId) {
        Optional<CourseConduction> optionalCourseConduction = courseConductionRepo.findById(Long.valueOf(id));

        if (!optionalCourseConduction.isPresent()){
            throw new RuntimeException("Class with Id: " + id+" was not found");
        }

        if(optionalCourseConduction.get().getLecturer().getId()!=lecturerId){
            throw new RuntimeException("You do not teach this class");
        }


        return conductionConverter.convert(optionalCourseConduction.get());
    }

    @Override
    public CourseConductionCommand saveCourseConductionCommand(CourseConductionCommand conductionCommand) {


        return null;
    }
}
