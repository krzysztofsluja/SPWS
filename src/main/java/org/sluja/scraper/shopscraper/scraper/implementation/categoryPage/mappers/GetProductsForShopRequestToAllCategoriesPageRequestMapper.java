package org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.mappers;

import org.mapstruct.*;
import org.sluja.scraper.shopscraper.dtos.request.GetProductsForShopRequest;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.dtos.AllCategoriesPageRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GetProductsForShopRequestToAllCategoriesPageRequestMapper {
    
    @Mappings({
        @Mapping(target = "mainPageUrl", source = "scrapingAttributes.homePageUrl"),
        @Mapping(target = "allCategoriesPageAttribute", source = "scrapingAttributes.allCategoriesUrls"),
        @Mapping(target = "pageUrlExtractAttribute", source = "scrapingAttributes.allCategoriesAttribute"),
        @Mapping(target = "shopName", source = "shopWithCategories.shopName")
    })
    AllCategoriesPageRequest map(final GetProductsForShopRequest request);
}
