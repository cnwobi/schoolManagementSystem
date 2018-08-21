package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionToCourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerToLecturerCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class LecturerControllerTest {
    @Mock
    LecturerService lecturerService;

    LecturerController controller;
    LecturerToLecturerCommand converterLecturer;
    CourseConductionToCourseConductionCommand conductionConverter;

    MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new LecturerController(lecturerService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        converterLecturer = new LecturerToLecturerCommand();
        conductionConverter = new CourseConductionToCourseConductionCommand();
    }

    @Test
    public void testGetCourseView() throws Exception {
        Lecturer lecturer = new Lecturer();
        lecturer.setId(1l);
        CourseConduction courseConduction = new CourseConduction();
        courseConduction.setId(2l);
        courseConduction.setLecturer(lecturer);
        List<CourseConductionCommand> conductionCommandList = new ArrayList<>();

        conductionCommandList.add(conductionConverter.convert(courseConduction));

        when(lecturerService.findLecturerById(1l)).thenReturn(converterLecturer.convert(lecturer));
        when(lecturerService.findCourseConductionByLecturerId(1l)).thenReturn(conductionCommandList);

        mockMvc.perform(get("/lecturer/1/view/courses")).andExpect(status().isOk())
                .andExpect(view().name("lecturer/courseview"))
                .andExpect(model().attributeExists("lecturer","coursesConducted"));
    }

    @Test
    public void testEditClassCapacity() {
        CourseConductionCommand courseConduction = new CourseConductionCommand();
        courseConduction.setId(1l);





    }
}