package org.sluja.scraper.shopscraper.scraper.categoryPage.service;

import lombok.RequiredArgsConstructor;
import org.sluja.scraper.shopscraper.dtos.request.GetProductsForShopRequest;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;
import org.sluja.scraper.shopscraper.scraper.categoryPage.dtos.AllCategoriesPageRequest;
import org.sluja.scraper.shopscraper.scraper.categoryPage.interfaces.ICategoryPageScraper;
import org.sluja.scraper.shopscraper.scraper.categoryPage.mappers.GetProductsForShopRequestToAllCategoriesPageRequestMapper;
import org.sluja.scraper.shopscraper.scraper.service.IGetScrapedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapeAllCategoriesPagesService implements IGetScrapedData<List<String>> {

    private final ICategoryPageScraper<String, AllCategoriesPageRequest> allCategoriesPageScraper;
    @Autowired
    private GetProductsForShopRequestToAllCategoriesPageRequestMapper mapper;
    @Override
    public List<String> getScrapedData(final GetProductsForShopRequest request) throws ExceptionWithErrorAndMessageCode {
        try {
            return allCategoriesPageScraper.getPages(mapper.map(request));
        } catch (final ExceptionWithErrorAndMessageCode e) {
            //TODO log
            throw e;
        }
    }
}
