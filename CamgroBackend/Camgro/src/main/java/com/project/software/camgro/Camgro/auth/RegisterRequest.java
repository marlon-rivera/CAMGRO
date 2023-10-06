package com.project.software.camgro.Camgro.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String id_personas;
    private String nombre_personas;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String departamento;
}
