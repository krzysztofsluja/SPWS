package org.sluja.scraper.shopscraper.product.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public record ProductDto(String id,
                         String name,
                         String shopName,
                         BigDecimal price,
                         List<String> productUrls,
                         List<String> imageProductUrls,
                         String category,
                         String context) implements Serializable {
}
