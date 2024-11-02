package org.sluja.scraper.shopscraper.connector.service.crawler;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.sluja.scraper.shopscraper.connector.dtos.request.crawler.CrawlerConnectRequest;
import org.sluja.scraper.shopscraper.connector.exceptions.connection.WebsiteConnectionTimeoutException;
import org.sluja.scraper.shopscraper.connector.exceptions.request.IncorrectConnectionRequestStructureException;
import org.sluja.scraper.shopscraper.connector.interfaces.IConnector;
import org.sluja.scraper.shopscraper.connector.interfaces.IWebsiteConnector;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebsiteCrawlerConnector implements IWebsiteConnector<WebDriver> {

    private final IConnector<Void, CrawlerConnectRequest> crawlerConnector;

    @Override
    public WebDriver getWebpage(String url) throws ExceptionWithErrorAndMessageCode {
        try(final CrawlerConnectRequest request = new CrawlerConnectRequest(url)) {
            crawlerConnector.connect(request);
            return request.getDriver();
        } catch (final IncorrectConnectionRequestStructureException ex) {
            //TODO ex
            //TODO log
            throw ex;
        } catch (WebsiteConnectionTimeoutException e) {
            //TODO ex
            //TODO log
            throw e;
        }
    }

    @Component
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
