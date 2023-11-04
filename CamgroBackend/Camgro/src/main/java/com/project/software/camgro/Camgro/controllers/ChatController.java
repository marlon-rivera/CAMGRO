package com.project.software.camgro.Camgro.controllers;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Chat;
import com.project.software.camgro.Camgro.domain.ErrorMesage;
import com.project.software.camgro.Camgro.domain.Message;
import com.project.software.camgro.Camgro.records.MessageRequest;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.ChatRepository;
import com.project.software.camgro.Camgro.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AccountRepository accountRepository;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageRequest message){
        System.out.println("Handling send message: " + message.content() + "to: " + to);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
    }

    @GetMapping(value = "all/{email}")
    public ResponseEntity<?> getAllChatsByAccount(@PathVariable("email") String email){
        Optional<Account> accountOp = accountRepository.findAccountByEmail(email);
        if(accountOp.isPresent()){
            List<Chat> chats = chatRepository.findAllByAccountBuyerOrAccountSeller(accountOp.get(), accountOp.get());

            return ResponseEntity.ok(chats);
        }
        return ResponseEntity.badRequest().body(new ErrorMesage("La cuenta no existe."));
    }

    @GetMapping(value = "all/messages/{idChat}")
    public ResponseEntity<?> getAllMessageByChat(@PathVariable("idChat") String idChat){
        Chat chat = chatRepository.findById("CH" + idChat).get();
        List<Message> messages = messageRepository.findAllByChat(chat);
        List<MessageRequest> result = new ArrayList<>();
        for (Message message :
                messages) {
            System.out.println(message);
            result.add(new MessageRequest(message.getAccount().getEmail(), null, message.getContentMessage()));
        }

        return ResponseEntity.ok(result);

    }

}
