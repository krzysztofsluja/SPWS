package org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.interfaces;

import org.jsoup.nodes.Document;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;

import java.util.Collection;
import java.util.List;

public interface IPageExtractor<T, R> {
    Collection<T> extractPages(Document document, R request) throws ExceptionWithErrorAndMessageCode;
} 