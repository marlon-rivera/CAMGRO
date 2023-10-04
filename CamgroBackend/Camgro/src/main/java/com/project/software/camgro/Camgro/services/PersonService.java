package com.project.software.camgro.Camgro.services;

import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

}
