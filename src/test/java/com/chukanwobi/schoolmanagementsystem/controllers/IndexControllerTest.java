package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.models.Lecturer;
import com.chukanwobi.schoolmanagementsystem.services.LecturerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IndexControllerTest {
    @Mock
    private LecturerService lecturerService;
@Mock
    Model model;

IndexController indexController;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(lecturerService);
    }
    @Test
    public void getIndex(){
        List<Lecturer> lecturers = new ArrayList<>();
        lecturers.add(new Lecturer());
        lecturers.add(new Lecturer());


        when(lecturerService.getLecturers()).thenReturn(lecturers);

        ArgumentCaptor<List<Lecturer>> listArgumentCaptor = ArgumentCaptor.forClass(List.class);
        String viewName = indexController.getIndex(model);
        assertEquals("index",viewName);
        verify(lecturerService,times(1)).getLecturers();
        verify(model,times(1)).addAttribute(eq("lecturers"),listArgumentCaptor.capture());
        List<Lecturer> setInController = listArgumentCaptor.getValue();
        assertEquals(2,setInController.size());
    }
}