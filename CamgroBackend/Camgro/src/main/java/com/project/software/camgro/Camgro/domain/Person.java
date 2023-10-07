package com.project.software.camgro.Camgro.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "id_personas")
    private String id;
    @Column(name =  "nombre_personas")
    private String name;
    @Column(name = "telefono")
    private String phone;
    @Column(name = "direccion")
    private String address;
    @ManyToOne
    @JoinColumn(name = "id_lugar_residencia")
    private Place place;
}
