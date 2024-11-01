package org.sluja.scraper.shopscraper.scraper.categoryPage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.sluja.scraper.shopscraper.connector.interfaces.IWebsiteConnector;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;
import org.sluja.scraper.shopscraper.scraper.categoryPage.dtos.AllCategoriesPageRequest;
import org.sluja.scraper.shopscraper.scraper.categoryPage.exceptions.EmptyCategoryPageElementsListException;
import org.sluja.scraper.shopscraper.scraper.categoryPage.interfaces.ICategoryPageScraper;
import org.sluja.scraper.shopscraper.scraper.service.AbstractBaseScraper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class AllCategoriesPageScraper extends AbstractBaseScraper implements ICategoryPageScraper<String, AllCategoriesPageRequest> {

    private final IWebsiteConnector<Document> websiteScraperConnector;

    @Override
    public List<String> getPages(final AllCategoriesPageRequest request) throws ExceptionWithErrorAndMessageCode {
        final Document document = websiteScraperConnector.getWebpage(request.mainPageUrl());
        final List<String> allCategoriesPageUrls = extractPages(document, request);
        if (CollectionUtils.isEmpty(allCategoriesPageUrls)) {
            log.warn("No category page URLs found for request: {}", request);
            throw new EmptyCategoryPageElementsListException();
        }
        return allCategoriesPageUrls;
    }

    @Override
    public List<String> extractPages(final Document document, final AllCategoriesPageRequest request) throws ExceptionWithErrorAndMessageCode {
        return request.allCategoriesPageAttribute()
                .stream()
                .map(property -> {
                    try {
                        final Elements elements = this.scrape(document, property);
                        return elements.eachAttr(request.pageUrlExtractAttribute());
                    } catch (final ExceptionWithErrorAndMessageCode e) {
                        log.error("Error extracting pages with property: {}", property, e);
                        return new ArrayList<String>();
                    }
                })
                .flatMap(List::stream)
                .map(element -> element.replaceAll("\\s", StringUtils.EMPTY))
                .distinct()
                .filter(StringUtils::isNotEmpty)
                .toList();
    }
}