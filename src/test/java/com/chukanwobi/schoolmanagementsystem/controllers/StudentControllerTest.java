package com.chukanwobi.schoolmanagementsystem.controllers;

import com.chukanwobi.schoolmanagementsystem.services.CourseService;
import org.junit.Before;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.Assert.*;

public class StudentControllerTest {
@Mock
@Qualifier("student")
    UserDetailsService userDetailsService;

@Mock
    CourseService courseService;
    @Before
    public void setUp() throws Exception {
    }
}