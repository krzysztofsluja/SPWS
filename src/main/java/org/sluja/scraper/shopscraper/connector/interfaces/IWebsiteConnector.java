package org.sluja.scraper.shopscraper.connector.interfaces;

public interface IWebsiteConnector<RTN> {

    RTN connectToWebpage(final String url);
}
