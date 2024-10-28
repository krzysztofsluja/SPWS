package org.sluja.scraper.shopscraper.scraper.categoryPage.dtos;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.scraper.categoryPage.exceptions.IncorrectCategoryPageRequestStructure;

import java.util.List;

public record AllCategoriesPageRequest(String mainPageUrl,
                                       List<String> allCategoriesPageAttribute,
                                       String pageUrlExtractAttribute,
                                       String shopName) {

    public AllCategoriesPageRequest {
        if(StringUtils.isEmpty(mainPageUrl)
                || CollectionUtils.isEmpty(allCategoriesPageAttribute)
                || allCategoriesPageAttribute.stream().anyMatch(StringUtils::isEmpty)
                || StringUtils.isEmpty(pageUrlExtractAttribute)
                || StringUtils.isEmpty(shopName))
            throw new IncorrectCategoryPageRequestStructure();
    }
}
