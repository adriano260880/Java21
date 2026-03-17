package br.com.aal.java21.virtualtheads.service;

import java.util.List;

public record ProductView(
        Product product,
        Inventory inventory,
        List<Review> reviews
) {}

