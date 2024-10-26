package org.sluja.scraper.shopscraper.connector.service.crawler;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.sluja.scraper.shopscraper.connector.dtos.request.crawler.CrawlerConnectRequest;
import org.sluja.scraper.shopscraper.connector.exceptions.connection.WebsiteConnectionTimeoutException;
import org.sluja.scraper.shopscraper.connector.exceptions.request.IncorrectConnectionRequestStructureException;
import org.sluja.scraper.shopscraper.connector.interfaces.IConnector;
import org.sluja.scraper.shopscraper.connector.interfaces.IWebsiteConnector;

public class WebsiteCrawlerConnector implements IWebsiteConnector<WebDriver> {

    private final IConnector<Void, CrawlerConnectRequest> crawlerConnector;

    public WebsiteCrawlerConnector() {
        crawlerConnector = new CrawlerConnector();
    }

    @Override
    public WebDriver getWebpage(String url) {
        try(final CrawlerConnectRequest request = new CrawlerConnectRequest(url)) {
            crawlerConnector.connect(request);
            return request.getDriver();
        } catch (final IncorrectConnectionRequestStructureException ex) {
            //TODO ex
            //TODO log
            return null;
        } catch (WebsiteConnectionTimeoutException e) {
            //TODO ex
            //TODO log
            return null;
        }
    }

    static class CrawlerConnector implements IConnector<Void, CrawlerConnectRequest> {
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
}
