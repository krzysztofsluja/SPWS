package org.sluja.scraper.shopscraper.dtos.request;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.dtos.request.attributes.ShopScrapingAttributes;
import org.sluja.scraper.shopscraper.exceptions.IncorrectGetProductsForShopRequestStructure;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public record GetProductsForShopRequest(String shopName,
                                        List<String> categories,
                                        ShopScrapingAttributes scrapingAttributes,
                                        String context,
                                        boolean dynamicWebsite) implements Serializable {

    public GetProductsForShopRequest {
        if(CollectionUtils.isEmpty(categories)
                || Objects.isNull(scrapingAttributes)
                || StringUtils.isEmpty(shopName)
                || StringUtils.isEmpty(context))
            throw new IncorrectGetProductsForShopRequestStructure();
    }
}
