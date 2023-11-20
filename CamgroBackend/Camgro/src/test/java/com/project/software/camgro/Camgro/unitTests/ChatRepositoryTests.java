package com.project.software.camgro.Camgro.unitTests;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Chat;
import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.ChatRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChatRepositoryTests {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void ChatRepository_Save_ReturnChat(){
        Person personSell = Person.builder().id("P100").name("TESTING").phone("1111111111").address("Address").build();
        Person personBuy = Person.builder().id("P101").name("TESTING").phone("1111111111").address("Address").build();

        Account accountSell = Account.builder().person(personSell).id("AC100").build();
        Account accountBuy = Account.builder().person(personBuy).id("AC101").build();

        personRepository.save(personSell);
        personRepository.save(personBuy);

        accountRepository.save(accountSell);
        accountRepository.save(accountBuy);

        Chat chat = Chat.builder().accountSeller(accountSell).accountBuyer(accountBuy).id("CH100").build();

        Chat chatSaved = chatRepository.save(chat);

        Assertions.assertThat(chatSaved.getId()).isEqualTo("CH100");
    }

    @Test
    public void ChatRepository_Modify_ModifyChat(){
        Person personSell = Person.builder().id("P100").name("TESTING").phone("1111111111").address("Address").build();
        Person personBuy = Person.builder().id("P101").name("TESTING").phone("1111111111").address("Address").build();

        Account accountSell = Account.builder().person(personSell).id("AC100").build();
        Account accountBuy = Account.builder().person(personBuy).id("AC101").build();

        personRepository.save(personSell);
        personRepository.save(personBuy);

        accountRepository.save(accountSell);
        accountRepository.save(accountBuy);

        Chat chat = Chat.builder().accountSeller(accountSell).accountBuyer(accountBuy).id("CH100").build();

        Chat chatSaved = chatRepository.save(chat);

        chatSaved.setId("CH101");

        Chat chatSaved1 = chatRepository.save(chatSaved);

        Assertions.assertThat(chatSaved1.getId()).isEqualTo("CH101");
    }

    @Test
    public void ChatRepository_Delete_DeleteChat(){
        chatRepository.deleteById("CH1");

        Optional<Chat> chatDeleted = chatRepository.findById("CH1");

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> chatDeleted.get());
    }
}
