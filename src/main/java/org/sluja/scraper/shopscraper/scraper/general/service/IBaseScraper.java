package org.sluja.scraper.shopscraper.scraper.general.service;

import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;

@FunctionalInterface
public interface IBaseScraper<RTN, T> {

    RTN scrape(final T page, final String property) throws ExceptionWithErrorAndMessageCode;
}
