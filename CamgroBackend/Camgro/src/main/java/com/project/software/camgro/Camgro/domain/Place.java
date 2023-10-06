package com.project.software.camgro.Camgro.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "lugares")
@Data
public class Place {

    @Id
    @Column(name = "id_lugares")
    private String idPlace;
    @Column(name = "tipo_lugar")
    private String typeOfPlace;
    @Column(name = "lug_id_lugares")
    private String lugIdLug;
    @Column(name = "nombre_lugar")
    private String namePlace;

}
