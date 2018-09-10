package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.commands.CourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.commands.LecturerCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConductionConverters.CourseConductionToCourseConductionCommand;
import com.chukanwobi.schoolmanagementsystem.converters.courseConverters.CourseToCourseCommand;
import com.chukanwobi.schoolmanagementsystem.converters.lecturerConverters.LecturerToLecturerCommand;
import com.chukanwobi.schoolmanagementsystem.converters.studentConverter.StudentToStudentCommand;
import com.chukanwobi.schoolmanagementsystem.models.CourseConduction;
import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.services.CourseConductionService;
import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LecturerControllerTest {
    private static final Long LONG_ID = 4l;
    @Mock
    LecturerService lecturerService;
    @Mock
    CourseConductionService conductionService;

    LecturerController controller;
    @Mock
    LecturerToLecturerCommand converterLecturer;
    @Mock
    CourseToCourseCommand courseCommandConverter;
    @Mock
    UserDetailsService userDetailsService;
    CourseConductionToCourseConductionCommand conductionConverter;
    @Mock
    Authentication authentication;
    @Mock
    SecurityContext securityContext;
    MockMvc mockMvc;
    LecturerCommand command;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new LecturerCommand();
        command.setId(3l);

        controller = new LecturerController(lecturerService, conductionService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        converterLecturer = new LecturerToLecturerCommand();
        conductionConverter = new CourseConductionToCourseConductionCommand(new StudentToStudentCommand(), converterLecturer,courseCommandConverter );
    }

    @Test
    public void testGetCourseView() throws Exception {


        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Lecturer lecturer = new Lecturer();
        lecturer.setId(1l);
        CourseConduction courseConduction = new CourseConduction();
        courseConduction.setId(2l);
        courseConduction.setLecturer(lecturer);
        lecturer.setUsername("Test");
        List<CourseConductionCommand> conductionCommandList = new ArrayList<>();

        conductionCommandList.add(conductionConverter.convert(courseConduction));

        when(lecturerService.findLecturerByUsername(any())).thenReturn(converterLecturer.convert(lecturer));
        when(conductionService.findCourseConductionByLecturerId(1l)).thenReturn(conductionCommandList);
        when(lecturerService.findLecturerById(any())).thenReturn(converterLecturer.convert(lecturer));


        mockMvc.perform(get("/lecturer/1/view/courses")).andExpect(status().isOk())
                .andExpect(view().name("lecturer/dashboard"))
                .andExpect(model().attributeExists("lecturer", "coursesConducted"));
    }


    @Test
    public void testLoadLecturerDetails() throws Exception {
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(controller.authenticatedLecturer()).thenReturn(command);
        when(lecturerService.findLecturerByUsername(anyString())).thenReturn(command);
        mockMvc.perform(get("/lecturer"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testEditClassCapacity() throws Exception {
        CourseConductionCommand courseConduction = new CourseConductionCommand();
        LecturerCommand lecturerCommand = new LecturerCommand();
        lecturerCommand.setId(LONG_ID);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(controller.authenticatedLecturer()).thenReturn(lecturerCommand);

        when(conductionService.findCourseConductionByIdAndLecturerId(2l, 4l)).thenReturn(courseConduction);

        mockMvc.perform(get("/lecturer/4/class/2/editCapacity"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("courseConduction"))
                .andExpect(view().name("lecturer/class/edit-capacity"));


    }
}