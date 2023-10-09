package com.project.software.camgro.Camgro.staticdomain;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AccountCount {

    private final String PREFIX = "AC";
    private int number;
    @Autowired
    private AccountRepository accountRepository;

    private String id;
    private boolean isCalledDB = false;
    private static AccountCount accountCount;

    @Autowired
    private void setAccountRepository(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public static AccountCount getInstance(){
        if(accountCount == null){
            accountCount = new AccountCount();
        }
        return accountCount;
    }

    public Account getLastRecord(){
        return accountRepository.findTopByOrderByIdDesc();
    }
}
