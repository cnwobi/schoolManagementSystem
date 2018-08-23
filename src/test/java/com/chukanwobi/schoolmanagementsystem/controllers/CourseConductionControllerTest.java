package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionToCourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CourseConductionControllerTest {
@Mock
    LecturerService lecturerService;
    CourseConductionToCourseConductionCommand conductionConverter;;
    MockMvc mockMvc;
    CourseConductionController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new CourseConductionController(lecturerService);
        conductionConverter = new CourseConductionToCourseConductionCommand();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testEditClassCapacity() throws Exception {
        CourseConduction courseConduction = new CourseConduction();


        when(lecturerService.findCourseConductionByIdAndLecturerId(2l,4l)).thenReturn(conductionConverter.convert(
                courseConduction));

        mockMvc.perform(get("/lecturer/4/class/2/editCapacity"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("class"))
                .andExpect(view().name("courseConduction/form"));


    }
}