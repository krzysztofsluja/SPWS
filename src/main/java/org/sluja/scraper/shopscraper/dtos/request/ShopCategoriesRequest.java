package org.sluja.scraper.shopscraper.dtos.request;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.exceptions.IncorrectShopCategoriesRequestStructureException;

import java.util.List;

public record ShopCategoriesRequest(String shopName, List<String> categories) {

    public ShopCategoriesRequest {
        if (StringUtils.isEmpty(shopName) || CollectionUtils.isEmpty(categories)) {
            throw new IncorrectShopCategoriesRequestStructureException();
        }
    }
} 