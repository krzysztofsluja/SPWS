package org.sluja.scraper.shopscraper.scraper.service;

import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;
import org.sluja.scraper.shopscraper.scraper.categoryPage.exceptions.EmptyCategoryPageElementsListException;

@FunctionalInterface
public interface IBaseScraper<RTN, T> {

    RTN scrape(final T page, final String property) throws ExceptionWithErrorAndMessageCode;
}
