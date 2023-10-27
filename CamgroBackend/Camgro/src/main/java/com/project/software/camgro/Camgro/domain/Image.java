package com.project.software.camgro.Camgro.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="imagenes")
public class Image {

    @Id
    @Column(name="id_iamgenes")
    private String idImage;
    @ManyToOne
    @JoinColumn(name="id_publicacion")
    private Post post;
    @Column(name="url_image")
    private byte[] url;
    @Column(name="nombre_image")
    private String nameImage;
    @Column(name="fecha_carga")
    private LocalDate uploadDate;

}
