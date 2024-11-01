package org.sluja.scraper.shopscraper.scraper.categoryPage.service;

import lombok.RequiredArgsConstructor;
import org.sluja.scraper.shopscraper.dtos.request.GetProductsForShopRequest;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;
import org.sluja.scraper.shopscraper.scraper.categoryPage.dtos.AllCategoriesPageRequest;
import org.sluja.scraper.shopscraper.scraper.categoryPage.interfaces.ICategoryPageScraper;
import org.sluja.scraper.shopscraper.scraper.categoryPage.mappers.GetProductsForShopRequestToAllCategoriesPageRequestMapper;
import org.sluja.scraper.shopscraper.scraper.service.IGetScrapedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScrapeAllCategoriesPagesService implements IGetScrapedData<Map<String,String>> {

    private final ICategoryPageScraper<String, AllCategoriesPageRequest> allCategoriesPageScraper;
    @Autowired
    private GetProductsForShopRequestToAllCategoriesPageRequestMapper mapper;
    @Override
    @Cacheable(value = "categoryPagesForShop", keyGenerator = "categoryPagesForShopKeyGenerator", unless = "#result.isEmpty()")
    public Map<String,String> getScrapedData(final GetProductsForShopRequest request) throws ExceptionWithErrorAndMessageCode {
        try {
            final List<String> allCategoriesPages = allCategoriesPageScraper.getPages(mapper.map(request));
            return allCategoriesPages.stream()
                    .collect(Collectors.toMap(Function.identity(), Function.identity()));
        } catch (final ExceptionWithErrorAndMessageCode e) {
            //TODO log
            throw e;
        }
    }
}
