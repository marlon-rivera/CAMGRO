package com.project.software.camgro.Camgro.records;


import org.springframework.web.multipart.MultipartRequest;

import java.time.LocalDate;

public record AddPostRequest(MultipartRequest image, String name, String description, double price, String unit, LocalDate postDate, LocalDate harvestDate, int quantity, String postState) {
}
