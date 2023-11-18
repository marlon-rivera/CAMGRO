package com.project.software.camgro.Camgro.services;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Place;
import com.project.software.camgro.Camgro.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public String getNewId(){
        Optional<Place> place = placeRepository.findLastRecord();
        String id;
        int number;
        if(place.isPresent()){
            id = place.get().getIdPlace();
            number = Integer.parseInt(id.substring(2));
        }else {
            number = 0;
        }
        number++;
        id = "PL" + number;
        return id;
    }

}
