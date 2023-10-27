package com.project.software.camgro.Camgro.records;

import java.time.LocalDate;

public record AddPostRequest(String name, String description, double price, String unit, LocalDate postDate, LocalDate harvestDate, int quantity, String postState) {
}
