package org.sluja.scraper.shopscraper.dtos.request.attributes;

public record ShopScrapingAttributes(String productPrice,
                                     String productDiscountPrice,
                                     String homePageUrl,
                                     String productDescription,
                                     String productDescriptionAttribute,
                                     String productName,
                                     String productImageUrl,
                                     String productImageAttribute,
                                     String productDetailsUrl,
                                     String productDetailsAttribute,
                                     String productInstanceContainer,
                                     String categoryPagePagination,
                                     String allCategoriesUrl,
                                     String allCategoriesAttribute,
                                     String categoryUrlAttribute) {
}
