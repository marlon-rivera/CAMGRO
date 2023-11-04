package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {

    List<Chat> findAllByAccountBuyerOrAccountSeller(Account account, Account account1);

    Optional<Chat> findTopByOrderByIdDesc();
}
