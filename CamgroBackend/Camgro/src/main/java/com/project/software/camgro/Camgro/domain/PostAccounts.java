package com.project.software.camgro.Camgro.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "publicaciones_cuentas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostAccounts {

    @Id
    private PostsAccountsPrimaryKey postsAccountsPrimaryKey;

    @Column(name = "propia")
    private boolean own;

}


