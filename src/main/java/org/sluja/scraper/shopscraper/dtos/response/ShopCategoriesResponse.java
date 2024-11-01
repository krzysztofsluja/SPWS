package org.sluja.scraper.shopscraper.dtos.response;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.connector.exceptions.request.IncorrectConnectionRequestStructureException;
import org.sluja.scraper.shopscraper.scraper.categoryPage.exceptions.IncorrectShopCategoriesResponseStructureException;

import java.util.List;
import java.util.Map;

public record ShopCategoriesResponse(String shopName, 
                                     Map<String, List<String>> categoryUrls) {

    public ShopCategoriesResponse {
        validate(shopName, categoryUrls);
    }

    private static void validate(String shopName, Map<String, List<String>> categoryUrls) {
        if (StringUtils.isEmpty(shopName)
            || MapUtils.isEmpty(categoryUrls)) {
            throw new IncorrectShopCategoriesResponseStructureException();
        }
    }
} 