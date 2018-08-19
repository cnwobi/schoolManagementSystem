package com.chukanwobi.schoolmanagementsystem.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {
@GetMapping({"","/","/index"})
    public String getIndex(){
System.out.print("my name is bucker");
        return "index";
    }

}
