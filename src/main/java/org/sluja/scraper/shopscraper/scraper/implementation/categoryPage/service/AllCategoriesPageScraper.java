package org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.sluja.scraper.shopscraper.connector.interfaces.IWebsiteConnector;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.dtos.AllCategoriesPageRequest;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.exceptions.EmptyCategoryPageElementsListException;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.interfaces.ICategoryPageScraper;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.interfaces.IPageExtractor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllCategoriesPageScraper implements 
        ICategoryPageScraper<String, AllCategoriesPageRequest>,
        IPageExtractor<String, AllCategoriesPageRequest> {

    private final IWebsiteConnector<Document> websiteScraperConnector;
    private final CategoryUrlProcessor urlProcessor;

    @Override
    public Set<String> getPages(final AllCategoriesPageRequest request)
            throws ExceptionWithErrorAndMessageCode {
        log.debug("Starting to fetch pages for request: {}", request);
        try {
            final Document document = websiteScraperConnector.getWebpage(request.mainPageUrl());
            return extractPages(document, request);
        } catch (EmptyCategoryPageElementsListException e) {
            log.error("No pages found for request: {}", request);
            throw e;
        }
    }

    @Override
    public Set<String> extractPages(final Document document,
                                    final AllCategoriesPageRequest request)
            throws EmptyCategoryPageElementsListException {
        return urlProcessor.processUrls(
                request.allCategoriesPageAttribute(),
                document,
                request.pageUrlExtractAttribute()
        );
    }
}