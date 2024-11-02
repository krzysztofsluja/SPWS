package org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sluja.scraper.shopscraper.dtos.request.GetProductsForShopRequest;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.dtos.AllCategoriesPageRequest;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.interfaces.ICategoryPageScraper;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.mappers.GetProductsForShopRequestToAllCategoriesPageRequestMapper;
import org.sluja.scraper.shopscraper.scraper.general.service.IGetScrapedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScrapeAllCategoriesPagesService implements IGetScrapedData<Map<String,String>> {

    private final ICategoryPageScraper<String, AllCategoriesPageRequest> allCategoriesPageScraper;
    @Autowired
    private GetProductsForShopRequestToAllCategoriesPageRequestMapper mapper;
    @Override
    @Cacheable(value = "categoryPagesForShop", keyGenerator = "categoryPagesForShopKeyGenerator", unless = "#result.isEmpty()")
    public Map<String,String> getScrapedData(final GetProductsForShopRequest request) throws ExceptionWithErrorAndMessageCode {
        try {
            final Set<String> allCategoriesPages = (Set<String>) allCategoriesPageScraper.getPages(mapper.map(request));
            log.debug("Found {} category pages for shop: {}",
                    allCategoriesPages.size(),
                    request.shopWithCategories().shopName());
            return allCategoriesPages.stream()
                    .collect(Collectors.toMap(Function.identity(), Function.identity()));
        } catch (final ExceptionWithErrorAndMessageCode e) {
            log.error("Failed to scrape category pages for shop: {}. Error: {}",
                    request.shopWithCategories().shopName(),
                    e.getMessage());
            throw e;
        }
    }
}
