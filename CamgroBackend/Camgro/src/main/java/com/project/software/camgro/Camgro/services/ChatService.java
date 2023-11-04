package com.project.software.camgro.Camgro.services;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Chat;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    private int number;
    private String id;

    public String getNewID(){
        Optional<Chat> chat = chatRepository.findTopByOrderByIdDesc();
        if(chat.isPresent()){
            id = chat.get().getId();
            number = Integer.parseInt(id.substring(2));
        }else {
            number = 0;
        }
        number++;
        id = "CH" + number;
        return id;
    }

}
