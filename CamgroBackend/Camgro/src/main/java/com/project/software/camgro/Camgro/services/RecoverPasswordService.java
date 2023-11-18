package com.project.software.camgro.Camgro.services;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class RecoverPasswordService {

    @Autowired
    private AccountRepository accountRepository;
    private String code;
    private LocalDateTime dateExpiration;
    private Optional<Account> account;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public String generateCode(String email){
        account = accountRepository.findAccountByEmail(email);
        if(account.isEmpty()){
            throw new UsernameNotFoundException("No hay una cuenta registrada con el email ingresado.");
        }
        code = generateCode();
        dateExpiration = LocalDateTime.now().plusMinutes(10);
        return code;
    }

    private String generateCode() {
        Random random = new Random();
        int code = 1000 + random.nextInt(9000);
        return String.valueOf(code);
    }

    public boolean validateExpirationDate(){
        return LocalDateTime.now().isBefore(dateExpiration);
    }

    public boolean validateCode(String inputCode){
        return this.code.equals(inputCode);
    }

    public void changePassword(String password){
        System.out.println(encoder.encode(password));
        Account accountAux = account.get();
        accountAux.setPassword(encoder.encode(password));
        accountRepository.save(accountAux);
    }
}