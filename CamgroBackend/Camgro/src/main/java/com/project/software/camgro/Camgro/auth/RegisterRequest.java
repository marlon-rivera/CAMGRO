package com.project.software.camgro.Camgro.auth;

import com.project.software.camgro.Camgro.domain.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String phone;
    private String address;
    private String city;
    private String department;
    private String email;
    private String password;
}
