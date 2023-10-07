package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, String> {
}
