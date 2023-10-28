package com.project.software.camgro.Camgro.records;

import com.project.software.camgro.Camgro.domain.Place;

import java.time.LocalDate;

public record ResponseAllPosts(String idPost, int accountProducts, String descriptionPost, String postStatus, String postTitle, double priceProduct, LocalDate harvestDate, LocalDate postDate, byte[] image, String unit, Place place) { }
