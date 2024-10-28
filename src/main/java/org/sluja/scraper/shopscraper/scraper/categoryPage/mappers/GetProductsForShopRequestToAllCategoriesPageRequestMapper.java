package org.sluja.scraper.shopscraper.scraper.categoryPage.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.sluja.scraper.shopscraper.dtos.request.GetProductsForShopRequest;
import org.sluja.scraper.shopscraper.scraper.categoryPage.dtos.AllCategoriesPageRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GetProductsForShopRequestToAllCategoriesPageRequestMapper {

    @Mapping(target = "mainPageUrl", source = "scrapingAttributes.homePageUrl")
    @Mapping(target = "allCategoriesPageAttribute", source = "scrapingAttributes.allCategoriesUrls")
    @Mapping(target = "pageUrlExtractAttribute", source = "scrapingAttributes.allCategoriesAttribute")
    AllCategoriesPageRequest map(final GetProductsForShopRequest request);
}
