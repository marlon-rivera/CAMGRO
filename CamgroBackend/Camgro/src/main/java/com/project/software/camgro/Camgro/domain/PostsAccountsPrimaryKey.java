package com.project.software.camgro.Camgro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsAccountsPrimaryKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "id_publicacion")
    private Post post;

}
