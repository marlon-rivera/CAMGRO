package com.project.software.camgro.Camgro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class PersonController {

    @PostMapping(value = "prueba")
    public String welcome(){
        return "Welcome to from secure endpoint";
    }

}
