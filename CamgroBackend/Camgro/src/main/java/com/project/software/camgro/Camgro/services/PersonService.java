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

    public String getNewId(){
        Optional<Person> person = personRepository.findLastRecord();
        int number;
        String id;
        if(person.isPresent()){
            id = person.get().getId();
            System.out.println(id);
            number = Integer.parseInt(id.substring(1));
        }else {
                number = 0;
        }

        number++;
        id = "P" + number;
        return id;
    }

}
