package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Chat;
import com.project.software.camgro.Camgro.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, String> {

    List<Chat> findAllByAccountBuyerOrAccountSeller(Account account, Account account1);

    @Query("SELECT ch FROM Chat ch WHERE CAST(SUBSTRING(ch.id, 3)  AS INTEGER) = (SELECT MAX(CAST(SUBSTRING(ch2.id, 3) AS int)) FROM Chat ch2)")
    Optional<Chat> findLastRecord();

    @Query("SELECT c FROM Chat c WHERE (c.accountSeller = :account1 AND c.accountBuyer = :account2) OR (c.accountSeller = :account2 AND c.accountBuyer = :account1)")
    Optional<Chat> findChatByAccountBuyerAndAccounteSeller(Account account1, Account account2);

    @Query("SELECT m FROM Message m WHERE m.chat = :chat ORDER BY m.id DESC LIMIT 1")
    Optional<Message> findLastMessageByChat(Chat chat);
}
