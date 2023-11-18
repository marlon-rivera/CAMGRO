package com.project.software.camgro.Camgro.services;

import com.project.software.camgro.Camgro.domain.Image;
import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.repositories.ImageRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String getNewId(){
        Optional<Image> image = imageRepository.findLastRecord();
        int number;
        String id;
        if(image.isPresent()){
            id = image.get().getIdImage();
            number = Integer.parseInt(id.substring(1));
        }else {
            number = 0;
        }
        number++;
        id = "I" + number;
        return id;
    }

}
