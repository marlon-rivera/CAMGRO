package com.project.software.camgro.Camgro.controllers;

import com.project.software.camgro.Camgro.auth.AuthResponse;
import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.ErrorMesage;
import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.records.SearchPersonRequest;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    @GetMapping(value ="search/{email}")
    public ResponseEntity<?> searchPerson(@PathVariable("email") String email){
        System.out.println(email);
        Optional<Account> account = accountRepository.findAccountByEmail(email);
        if(account.isPresent()) {
            return ResponseEntity.ok(account.get().getPerson());
        }
        return ResponseEntity.badRequest().body(new ErrorMesage("Cuenta no encontrada."));

    }

    @PostMapping(value = "modify")
    public ResponseEntity<?> modifyAccount(@)



}
