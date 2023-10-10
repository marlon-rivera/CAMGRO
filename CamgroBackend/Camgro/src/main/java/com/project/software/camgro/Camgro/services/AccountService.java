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

    private boolean last;
    private int number;
    private String id;

    public String getNewID(){
        if(!last){
            Optional<Account> account = accountRepository.findTopByOrderByIdDesc();
            if(account.isPresent()){
                id = account.get().getId();
                number = Integer.parseInt(id.substring(2));
            }else {
                number = 0;
            }
            last = true;
        }
        number++;
        id = "AC" + number;
        return id;
    }
}
