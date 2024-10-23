package org.sluja.scraper.shopscraper.dtos.request;

import org.sluja.scraper.shopscraper.dtos.request.attributes.ShopScrapingAttributes;

import java.io.Serializable;
import java.util.List;

public record GetProductsForShopRequest(String shopName,
                                        List<String> categories,
                                        List<ShopScrapingAttributes> scrapingAttributes,
                                        String context,
                                        boolean dynamicWebsite) implements Serializable {
}
