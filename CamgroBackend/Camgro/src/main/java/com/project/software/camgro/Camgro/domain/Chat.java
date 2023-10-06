package com.project.software.camgro.Camgro.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "chats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @Column(name = "id_chat")
    private String id;
    @ManyToOne
    @JoinColumn(name = "id_cuenta_vendedor")
    private Account accountSeller;
    @ManyToOne
    @JoinColumn(name = "id_cuenta_comprador")
    private Account accountBuyer;
    @Column(name = "fecha_creacion")
    private LocalDateTime creation_date;

}
