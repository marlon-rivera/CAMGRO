package com.project.software.camgro.Camgro.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lugares")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Place {

    @Id
    @Column(name = "id_lugares")
    private String idPlace;
    @Column(name = "tipo_lugar")
    private String typeOfPlace;
    @ManyToOne
    @JoinColumn(name = "lug_id_lugares")
    private Place lugIdLug;
    @Column(name = "nombre_lugar")
    private String namePlace;

}
