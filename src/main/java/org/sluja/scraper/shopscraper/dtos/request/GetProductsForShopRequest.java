package org.sluja.scraper.shopscraper.dtos.request;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.dtos.request.attributes.ShopScrapingAttributes;
import org.sluja.scraper.shopscraper.exceptions.IncorrectGetProductsForShopRequestStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public record GetProductsForShopRequest(ShopCategoriesRequest shopWithCategories,
                                        Map<String, List<String>> categoriesProperties,
                                        ShopScrapingAttributes scrapingAttributes,
                                        String context,
                                        boolean dynamicWebsite) implements Serializable {

    public GetProductsForShopRequest(final ShopCategoriesRequest shopWithCategories,
                                     final Map<String, List<String>> categoriesProperties,
                                     final ShopScrapingAttributes scrapingAttributes,
                                     final String context,
                                     final boolean dynamicWebsite) {
        if(Objects.isNull(scrapingAttributes)
                || StringUtils.isEmpty(context))
            throw new IncorrectGetProductsForShopRequestStructure();
            
        this.shopWithCategories = shopWithCategories;
        this.categoriesProperties = categoriesProperties;
        this.scrapingAttributes = scrapingAttributes;
        this.context = context;
        this.dynamicWebsite = dynamicWebsite;
        
        addCategoryNameToProperties();
    }

    private void addCategoryNameToProperties() {
        shopWithCategories.categories().forEach(category -> categoriesProperties.computeIfAbsent(category, _ -> new ArrayList<>())
                            .add(category));
    }
}
