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
        if(StringUtils.isBlank(mainPageUrl)
                || CollectionUtils.isEmpty(allCategoriesPageAttribute)
                || allCategoriesPageAttribute.stream().anyMatch(StringUtils::isBlank)
                || StringUtils.isBlank(pageUrlExtractAttribute)
                || StringUtils.isBlank(shopName))
            throw new IncorrectCategoryPageRequestStructure();
    }

    private static boolean validate(final String mainPageUrl,
                                     final List<String> allCategoriesPageAttribute,
                                     final String pageUrlExtractAttribute,
                                     final String shopName) {
        return CollectionUtils.isNotEmpty(allCategoriesPageAttribute)
                && allCategoriesPageAttribute.stream().allMatch(StringUtils::isNotBlank)
                && StringUtils.isNotBlank(mainPageUrl)
                && StringUtils.isNotBlank(pageUrlExtractAttribute)
                && StringUtils.isNotBlank(shopName);
    }

    private static boolean isValid(final String mainPageUrl,
                                   final List<String> allCategoriesPageAttribute,
                                   final String pageUrlExtractAttribute,
                                   final String shopName) {
        return validate(mainPageUrl, allCategoriesPageAttribute, pageUrlExtractAttribute, shopName);
    }

    private static boolean isNotValid(final String mainPageUrl,
                                      final List<String> allCategoriesPageAttribute,
                                      final String pageUrlExtractAttribute,
                                      final String shopName) {
        return !isValid(mainPageUrl, allCategoriesPageAttribute, pageUrlExtractAttribute, shopName);
    }
}
