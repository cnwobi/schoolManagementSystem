package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerToLecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.repositories.LecturerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j

public class LectureServiceImpl implements LecturerService {

private LecturerRepository lecturerRepository;
private LecturerToLecturerCommand converter;



    public LectureServiceImpl(LecturerRepository lecturerRepository,
                              LecturerToLecturerCommand converter

                            ) {
        this.lecturerRepository = lecturerRepository;
        this.converter = converter;

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

      Lecturer lecturer = lecturerRepository.findLecturerByUsername(username).orElseThrow(()->new RuntimeException("Lecturer not found"));

      return converter.convert(lecturer);

    }
@Override
    public void uploadGrades(CourseConductionCommand conductionCommand){


}

}
