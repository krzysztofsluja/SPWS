package org.sluja.scraper.shopscraper.scraper.general.service;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.exceptions.EmptyCategoryPageElementsListException;

public abstract class AbstractBaseScraper implements IBaseScraper<Elements, Document> {

    @Override
    public Elements scrape(final Document document, final String property) throws ExceptionWithErrorAndMessageCode {
        final Elements elements = document.select(property);
        if(elements.isEmpty()) {
            throw new EmptyCategoryPageElementsListException();
        }
        return elements;
    }

}
