package org.sluja.scraper.shopscraper.connector.service.crawler;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.sluja.scraper.shopscraper.connector.dtos.request.ConnectRequest;
import org.sluja.scraper.shopscraper.connector.dtos.request.crawler.CrawlerConnectRequest;
import org.sluja.scraper.shopscraper.connector.exceptions.connection.WebsiteConnectionTimeoutException;
import org.sluja.scraper.shopscraper.connector.interfaces.IConnector;

public class WebsiteCrawlerConnector implements IConnector<Void, CrawlerConnectRequest> {
    @Override
    public Void connect(final CrawlerConnectRequest request) throws WebsiteConnectionTimeoutException {
        try(request){
            request.getDriver().get(request.getUrl());
        } catch (final TimeoutException _) {
            throw new WebsiteConnectionTimeoutException();
        }
        return null;
    }

}
