package org.sluja.scraper.shopscraper.connector.interfaces;

import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;

public interface IWebsiteConnector<RTN> {

    RTN getWebpage(final String url) throws ExceptionWithErrorAndMessageCode;
}
