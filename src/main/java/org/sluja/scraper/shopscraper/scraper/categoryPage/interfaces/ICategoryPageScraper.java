package org.sluja.scraper.shopscraper.scraper.categoryPage.interfaces;

import org.jsoup.nodes.Document;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;

import java.util.List;

public interface ICategoryPageScraper<RTN, RQT> {

    List<RTN> getPages(RQT request) throws ExceptionWithErrorAndMessageCode;
    List<RTN> extractPages(Document document, RQT request) throws ExceptionWithErrorAndMessageCode;
}
