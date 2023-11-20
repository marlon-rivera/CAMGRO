package com.project.software.camgro.Camgro.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.software.camgro.Camgro.auth.AuthResponse;
import com.project.software.camgro.Camgro.auth.LoginRequest;
import com.project.software.camgro.Camgro.domain.*;
import com.project.software.camgro.Camgro.records.MessageRequest;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.ChatRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import com.project.software.camgro.Camgro.repositories.PlaceRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class ChatControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ChatRepository chatRepository;

    @Test
    public void ChatController_FindAllByEmail_ReturnAllChatsByEmail() throws Exception {
        Place place = Place.builder().idPlace("PL100").namePlace("TESTING").typeOfPlace("D").lugIdLug(null).build();
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account = Account.builder().id("AC100").email("TESTING@123.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();

        placeRepository.save(place);
        personRepository.save(person);
        accountRepository.save(account);

        LoginRequest loginRequest = new LoginRequest("TESTING@123.com", "123");

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andReturn();

        AuthResponse authResponse = objectMapper.readValue(response.getResponse().getContentAsString(), AuthResponse.class);

        ResultActions results = mockMvc.perform(
                MockMvcRequestBuilders.get("/chat/all/TESTING@123.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + authResponse.getToken())
        );

        results.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ChatController_FindMessagesByChat_ReturnAllMessagesByChat() throws Exception {
        Place place = Place.builder().idPlace("PL100").namePlace("TESTING").typeOfPlace("D").lugIdLug(null).build();
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account = Account.builder().id("AC100").email("TESTING@123.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();

        Person person1 = Person.builder().id("P101").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account1 = Account.builder().id("AC101").email("TESTING@1234.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();


        placeRepository.save(place);
        personRepository.save(person);
        personRepository.save(person1);
        accountRepository.save(account);
        accountRepository.save(account1);

        Chat chat = new Chat("CH100", account, account1, LocalDateTime.now());

        chatRepository.save(chat);

        LoginRequest loginRequest = new LoginRequest("TESTING@123.com", "123");

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andReturn();

        AuthResponse authResponse = objectMapper.readValue(response.getResponse().getContentAsString(), AuthResponse.class);

        ResultActions results = mockMvc.perform(
                MockMvcRequestBuilders.get("/chat/all/messages/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + authResponse.getToken())
        );
        results.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ChatController_SendMessage_ReturnSendMessageOk() throws Exception {
        Place place = Place.builder().idPlace("PL100").namePlace("TESTING").typeOfPlace("D").lugIdLug(null).build();
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account = Account.builder().id("AC100").email("TESTING@123.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();

        Person person1 = Person.builder().id("P101").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account1 = Account.builder().id("AC101").email("TESTING@1234.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();


        placeRepository.save(place);
        personRepository.save(person);
        personRepository.save(person1);
        accountRepository.save(account);
        accountRepository.save(account1);

        Chat chat = new Chat("CH100", account, account1, LocalDateTime.now());

        chatRepository.save(chat);

        LoginRequest loginRequest = new LoginRequest("TESTING@123.com", "123");

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andReturn();

        AuthResponse authResponse = objectMapper.readValue(response.getResponse().getContentAsString(), AuthResponse.class);

        MessageRequest messageRequest = new MessageRequest(account.getEmail(), account1.getEmail(), "TESTING");

        ResultActions results = mockMvc.perform(
                MockMvcRequestBuilders.post("/chat/send/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + authResponse.getToken())
                        .content(objectMapper.writeValueAsString(messageRequest))
        );
        results.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
