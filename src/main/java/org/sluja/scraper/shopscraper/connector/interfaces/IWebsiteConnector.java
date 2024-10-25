package org.sluja.scraper.shopscraper.connector.interfaces;

public interface IWebsiteConnector<RTN> {

    RTN getWebpage(final String url);
}
