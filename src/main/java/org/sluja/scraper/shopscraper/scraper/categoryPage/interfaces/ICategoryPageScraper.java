package org.sluja.scraper.shopscraper.scraper.categoryPage.interfaces;

import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;

import java.util.List;

@FunctionalInterface
public interface ICategoryPageScraper<RTN, RQT> {

    List<RTN> getPages(RQT request) throws ExceptionWithErrorAndMessageCode;
}
