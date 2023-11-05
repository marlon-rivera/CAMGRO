package com.project.software.camgro.Camgro.controllers;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Chat;
import com.project.software.camgro.Camgro.domain.ErrorMesage;
import com.project.software.camgro.Camgro.domain.Message;
import com.project.software.camgro.Camgro.records.ChatsResponse;
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
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final AccountRepository accountRepository;
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    @GetMapping(value = "all/{email}")
    public ResponseEntity<?> getAllChatsByAccount(@PathVariable("email") String email){
        Optional<Account> accountOp = accountRepository.findAccountByEmail(email);
        if(accountOp.isPresent()){
            List<Chat> chats = chatRepository.findAllByAccountBuyerOrAccountSeller(accountOp.get(), accountOp.get());
            List<ChatsResponse> result = new ArrayList<>();
            for (Chat chat :
                    chats) {
                Optional<Message> messageOp = chatRepository.findLastMessageByChat(chat);
                if(messageOp.isPresent()){
                    if(chat.getAccountBuyer().getEmail().equalsIgnoreCase(email)){
                        result.add(new ChatsResponse(chat.getAccountSeller().getPerson().getName(), messageOp.get().getContentMessage(), chat.getAccountSeller().getEmail()));
                    }else{
                        result.add(new ChatsResponse(chat.getAccountBuyer().getPerson().getName(), messageOp.get().getContentMessage(), chat.getAccountBuyer().getEmail()));
                    }
                }
            }
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(new ErrorMesage("La cuenta no existe."));
    }

    @GetMapping("/{email1}/{email2}")
    public ResponseEntity<?> getChatBy2Accounts(@PathVariable("email1") String email1, @PathVariable("email2") String email2){
        Optional<Account> oneOp = accountRepository.findAccountByEmail(email1);
        Optional<Account> twoOp = accountRepository.findAccountByEmail(email2);
        if(oneOp.isPresent() && twoOp.isPresent()){
            System.out.println(oneOp.get());
            System.out.println(twoOp.get());
            Optional<Chat> chatOp = chatRepository.findChatByAccountBuyerAndAccounteSeller(oneOp.get(), twoOp.get());
            System.out.println(chatOp);
            return chatOp.map(chat -> ResponseEntity.ok(new ErrorMesage(chat.getId().substring(2)))).orElseGet(() -> ResponseEntity.ok(new ErrorMesage("No hay chat entre ambas cuentas")));
        }
        return ResponseEntity.badRequest().body(new ErrorMesage("Una de las cuentas no existe."));
    }

    @GetMapping(value = "all/messages/{idChat}")
    public ResponseEntity<?> getAllMessageByChat(@PathVariable("idChat") String idChat){
        Chat chat = chatRepository.findById("CH" + idChat).get();
        List<Message> messages = messageRepository.findAllByChat(chat);
        List<MessageRequest> result = new ArrayList<>();
        for (Message message :
                messages) {
            result.add(new MessageRequest(message.getAccount().getEmail(), null, message.getContentMessage()));
        }

        return ResponseEntity.ok(result);

    }

}
