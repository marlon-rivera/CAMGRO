package com.project.software.camgro.Camgro.records;

import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.domain.Role;

public record AccountResponse(String idAccount, String email, Role role, Person person) {
}
