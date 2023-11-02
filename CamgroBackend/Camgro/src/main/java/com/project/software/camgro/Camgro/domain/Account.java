package com.project.software.camgro.Camgro.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "cuentas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements UserDetails{

    @Id
    @Column(name = "id_cuenta")
    private String id;
    @OneToOne
    @JoinColumn(name = "id_personas")
    private Person person;
    @Column(name = "email_persona")
    private String email;
    @Column(name = "contrasena_cuenta")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_user")
    private Role role;
    @Column(name = "activa")
    private boolean active;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
