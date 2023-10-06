package com.project.software.camgro.Camgro.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "cuentas")
@Data
public class Account{

    @Id
    @Column(name = "id_cuenta")
    private String id;
    @Column(name = "id_personas")
    private String idPerson;
    @Column(name = "email_persona")
    private String email;
    @Column(name = "contaseña_persona")
    private String password;

}
