package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Chat;
import com.project.software.camgro.Camgro.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, String> {
    
    List<Message> findAllByChat(Chat chat);


    @Query("SELECT m FROM Message m WHERE CAST(SUBSTRING(m.id, 2)  AS INTEGER) = (SELECT MAX(CAST(SUBSTRING(m2.id, 2) AS int)) FROM Message m2)")
    Optional<Message> findLastRecord();

}
