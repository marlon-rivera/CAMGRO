package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findAccountByEmail(String email);

    Optional<Account> findTopByOrderByIdDesc();
}
