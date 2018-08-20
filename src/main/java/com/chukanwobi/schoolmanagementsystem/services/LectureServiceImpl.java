package com.chukanwobi.schoolmanagementsystem.services;

import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.repositories.LecturerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LectureServiceImpl implements LecturerService {

private LecturerRepository lecturerRepository;

    public LectureServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public List<Lecturer> getRecipes() {
        List<Lecturer> lecturers = new ArrayList<>();

        lecturerRepository.findAll().iterator().forEachRemaining(lecturers::add);
        return lecturers;
    }
}
