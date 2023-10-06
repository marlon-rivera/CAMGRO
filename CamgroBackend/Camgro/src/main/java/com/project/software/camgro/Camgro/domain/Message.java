package com.project.software.camgro.Camgro.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @Column(name = "id_mensaje")
    private String id;
    @ManyToOne
    @JoinColumn(name = "id_chat")
    private Chat chat;
    @Column(name = "contenido_mensaje")
    private String contentMessage;
    @Column(name = "fecha_mensaje")
    private LocalDateTime date;
    @Column(name = "id_cuenta")
    private String id_account;
}
