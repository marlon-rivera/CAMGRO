package com.project.software.camgro.Camgro.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "personas")
@Table(name = "personas")
@Data
public class Person {

    @Id
    private String id_personas;
    private String nombre_personas;
    private String telefono;
    private String direccion;
}
