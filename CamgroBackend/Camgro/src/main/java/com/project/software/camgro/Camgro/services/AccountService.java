package com.project.software.camgro.Camgro.services;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private int number;
    private String id;

    public String getNewID(){
        Optional<Account> account = accountRepository.findLastRecord();
        if(account.isPresent()){
            id = account.get().getId();
            number = Integer.parseInt(id.substring(2));
        }else {
            number = 0;
        }
        number++;
        id = "AC" + number;
        return id;
    }
}
