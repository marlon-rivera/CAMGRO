package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Chat;
import com.project.software.camgro.Camgro.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, String> {
    
    List<Message> findAllByChat(Chat chat);

    Optional<Message> findTopByOrderByIdDesc();
}
