package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionToCourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerToLecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.repositories.CourseConductionRepo;
import com.chukanwobi.schoolmanagementsystem.repositories.LecturerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LectureServiceImpl implements LecturerService {

private LecturerRepository lecturerRepository;
private LecturerToLecturerCommand converter;
private CourseConductionToCourseConductionCommand conductionConverter;
private CourseConductionRepo courseConductionRepo;

    public LectureServiceImpl(LecturerRepository lecturerRepository,
                              LecturerToLecturerCommand converter,
                              CourseConductionToCourseConductionCommand                                       conductionConverter,
                              CourseConductionRepo courseConductionRepo) {
        this.lecturerRepository = lecturerRepository;
        this.converter = converter;
        this.conductionConverter = conductionConverter;
        this.courseConductionRepo = courseConductionRepo;
    }

    @Override
    public List<Lecturer> getLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();

        lecturerRepository.findAll().iterator().forEachRemaining(lecturers::add);
        return lecturers;
    }

    @Override
    public LecturerCommand findLecturerById(Long lecturerId) {
        Optional<Lecturer> lecturerOptional = lecturerRepository.findById(lecturerId);
        if(!lecturerOptional.isPresent()){
            throw new RuntimeException("Lecturer Not found for Id:" + lecturerId);
        }


        return converter.convert(lecturerOptional.get());
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
    public List<CourseConductionCommand> findCourseConductionByLecturerId(Long lecturerId) {
        List<CourseConductionCommand> conductionCommandList = new ArrayList<>();

        courseConductionRepo.findAll().iterator().forEachRemaining(courseConduction -> conductionCommandList.add(conductionConverter.convert(courseConduction)));

        log.debug("\n\n\n\n\n\n\n\n\n"+conductionCommandList.toString());
        List<CourseConductionCommand> conductionCommandList1= new ArrayList<>();
        CourseConductionCommand courseConduction = conductionCommandList.stream().findFirst().get();
        log.debug(courseConduction.toString());

        conductionCommandList.stream()
                .filter(courseConductionCommand -> courseConductionCommand.getLecturer().getId()==lecturerId)
                .forEach(courseConductionCommand ->conductionCommandList1.add(courseConductionCommand) );
        return conductionCommandList1;
    }
}
