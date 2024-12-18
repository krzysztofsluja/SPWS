package org.sluja.scraper.shopscraper.dtos.response;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.exceptions.IncorrectShopCategoriesResponseStructureException;

import java.util.List;
import java.util.Map;

public record ShopCategoriesData(String shopName,
                                 Map<String, List<String>> categoryUrls) {

    public ShopCategoriesData {
        validate(shopName, categoryUrls);
    }

    private static void validate(String shopName, Map<String, List<String>> categoryUrls) {
        if (StringUtils.isBlank(shopName)
            || MapUtils.isEmpty(categoryUrls)
            || categoryUrls.keySet().stream().anyMatch(StringUtils::isBlank)
            || categoryUrls.values().stream().anyMatch(CollectionUtils::isEmpty)
            || categoryUrls.values().stream().flatMap(List::stream).anyMatch(StringUtils::isBlank)) {
            throw new IncorrectShopCategoriesResponseStructureException();
        }
    }
} 