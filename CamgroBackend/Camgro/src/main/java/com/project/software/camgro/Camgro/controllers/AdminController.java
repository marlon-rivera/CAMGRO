package com.project.software.camgro.Camgro.controllers;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.ErrorMesage;
import com.project.software.camgro.Camgro.records.AccountResponse;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    @GetMapping(value = "all-accounts")
    public ResponseEntity<?> getAllAccounts(){
        List<Account> accountList = accountRepository.findAll();
        List<AccountResponse> result = new ArrayList<>();
        for (Account account :
                accountList) {
            if(account.isActive()){
                result.add(new AccountResponse(account.getId().substring(2), account.getEmail(), account.getRole(), account.getPerson()));
            }

        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "delete-account/{idAccount}")
    public ResponseEntity<?> deleteAccoutn(@PathVariable("idAccount") String idAccount){
        Optional<Account> account = accountRepository.findById("AC" + idAccount);
        if(account.isPresent()){
            account.get().setActive(false);
            accountRepository.save(account.get());
        }
        return ResponseEntity.badRequest().body(new ErrorMesage("No se encontró la cuenta deseada."));
    }

}
