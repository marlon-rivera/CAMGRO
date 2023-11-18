package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findAccountByEmail(String email);

    Optional<Account> findByPerson(Person person);

    @Query("SELECT ac FROM Account ac WHERE CAST(SUBSTRING(ac.id, 3)  AS INTEGER) = (SELECT MAX(CAST(SUBSTRING(ac2.id, 3) AS int)) FROM Account ac2)")
    Optional<Account> findLastRecord();

    List<Account> findAllByActive(boolean active);
}
