package com.project.software.camgro.Camgro.services;

import com.project.software.camgro.Camgro.domain.Chat;
import com.project.software.camgro.Camgro.domain.Message;
import com.project.software.camgro.Camgro.repositories.ChatRepository;
import com.project.software.camgro.Camgro.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    private int number;
    private String id;

    public String getNewID(){
        Optional<Message> message = messageRepository.findLastRecord();
        if(message.isPresent()){
            id = message.get().getId();
            number = Integer.parseInt(id.substring(1));
        }else {
            number = 0;
        }
        System.out.println(number);
        number++;
        id = "M" + number;
        System.out.println(id);
        return id;
    }
}
