package com.project.software.camgro.Camgro.controllers;

import com.project.software.camgro.Camgro.auth.AuthResponse;
import com.project.software.camgro.Camgro.records.SearchPersonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    @GetMapping(value ="search")
    public ResponseEntity<?> searchPerson(@PathVariable String email){
        System.out.println(email);
        return ResponseEntity.ok(null);
    }

}
