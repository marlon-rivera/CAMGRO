package com.project.software.camgro.Camgro.records;


import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record AddPostRequest(MultipartFile image, String name, String description, double price, String unit, LocalDate postDate, LocalDate harvestDate, int quantity, String postState) {
}
