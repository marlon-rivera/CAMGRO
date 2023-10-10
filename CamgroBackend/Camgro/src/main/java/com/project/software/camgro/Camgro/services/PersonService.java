package com.project.software.camgro.Camgro.services;

import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.domain.Place;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import com.project.software.camgro.Camgro.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    private boolean last;
    private String id;
    private int number;

    public String getNewId(){
        if(!last){
            Optional<Person> person = personRepository.findTopByOrderByIdDesc();
            if(person.isPresent()){
                id = person.get().getId();
                number = Integer.parseInt(id.substring(2));
            }else {
                number = 0;
            }
            last = true;
        }
        number++;
        id = "P" + number;
        return id;
    }
}
