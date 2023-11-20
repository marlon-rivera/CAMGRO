package com.project.software.camgro.Camgro.unitTests;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void AccountRepository_Save_ReturnSaveAccoutn(){
        Account account = Account.builder().email("Testing@123.com").id("AC100").password("123").build();
        Account accountSave = accountRepository.save(account);
        Assertions.assertThat(accountSave.getId()).isEqualTo("AC100");
    }

    @Test
    public void AccountRepository_Delete_DeleteAccount(){
        accountRepository.deleteById("AC1");
        Optional<Account> accountDeleted = accountRepository.findById("AC1");

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> accountDeleted.get());
    }

    @Test
    public void AccountRepository_Modify_ModifyAccount(){
        Optional<Account> account =  accountRepository.findById("AC1");
        Account accountModifed = account.get();
        accountModifed.setEmail("TESTING@123.com");
        Account modified = accountRepository.save(accountModifed);
        Assertions.assertThat(modified.getEmail()).isEqualTo("TESTING@123.com");
    }

    @Test
    public void AccountRepository_Find_FindAccountsByActive(){
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("Address").build();
        personRepository.save(person);
        Account account1 = Account.builder().active(false).person(person).id("AC101").email("TESTING1@123.com").password("123").build();
        Account account2 = Account.builder().active(false).person(person).id("AC102").email("TESTING12@123.com").password("123").build();
        Account account3 = Account.builder().active(false).person(person).id("AC103").email("TESTING123@123.com").password("123").build();

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);

        List<Account> accountList = accountRepository.findAllByActive(false);

        Assertions.assertThat(accountList.size()).isEqualTo(3);
    }

}
