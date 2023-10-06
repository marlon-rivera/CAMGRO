package com.project.software.camgro.Camgro.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Table
@Entity(name="publicaciones")
@Data
public class Post {

    @Id
    @Column(name="id_publicacion")
    private String idPost;
    @ManyToOne
    @JoinColumn(name="id_cuenta")
    private Account account;
    @ManyToOne
    @JoinColumn(name="id_lugares")
    private Place place;
    @Column(name="precio_productos")
    private double priceProduct;
    @Column(name="cantidad_productos")
    private int amountProducts;
    @Column(name="titulo_publicacion")
    private String postTitle;
    @Column(name="descipcion_publicacion")
    private String descriptionPost;
    @Column(name="fecha_publicacion")
    private LocalDate publicationDate;
    @Column(name="fecha_cosecha")
    private LocalDate harvestDate;
    @Column(name="unidad_medida")
    private String measureUnit;
    @Column(name="estado_publicacion")
    private String postStatus;


}
