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
    private boolean last;
    private String id;
    private int number;

    public String getNewId(){
        if(!last){
            Optional<Place> place = placeRepository.findTopByOrderByIdPlaceDesc();
            if(place.isPresent()){
                id = place.get().getIdPlace();
                number = Integer.parseInt(id.substring(2));
            }else {
                number = 0;
            }
            last = true;
        }
        number++;
        id = "PL" + number;
        return id;
    }

}
