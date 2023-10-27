package com.project.software.camgro.Camgro.records;

import java.time.LocalDate;

public record ResponseAllPosts(int accountProducts, String descriptionPost, String postStatus, String postTitle, double priceProduct, LocalDate harvestDate, LocalDate postDate, byte[] image) { }
