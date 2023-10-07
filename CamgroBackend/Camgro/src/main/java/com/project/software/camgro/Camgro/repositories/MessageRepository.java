package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {
}
