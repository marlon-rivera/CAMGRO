package com.project.software.camgro.Camgro.records;

import com.project.software.camgro.Camgro.domain.Place;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record ModifyPersonRequest(String email, String name, String phone, String address, String department, String city) {
}
