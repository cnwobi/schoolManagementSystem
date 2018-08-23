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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
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
    public LecturerCommand findLecturerByUsername(String username) {

        Optional<Lecturer> lecturerOptional = lecturerRepository.findLecturerByUsername(username);
        if(!lecturerOptional.isPresent()){
            throw new RuntimeException("Lecturer with username: "+username+" was not found");
        }

        return converter.convert(lecturerOptional.get());

    }


}
