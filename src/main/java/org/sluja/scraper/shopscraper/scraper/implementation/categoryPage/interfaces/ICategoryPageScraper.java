package org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.interfaces;

import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;

import java.util.Collection;
import java.util.List;

public interface ICategoryPageScraper<T, R> {
    Collection<T> getPages(R request) throws ExceptionWithErrorAndMessageCode;
}
