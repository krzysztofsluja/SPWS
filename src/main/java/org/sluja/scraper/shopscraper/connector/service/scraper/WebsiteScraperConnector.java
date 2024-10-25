package org.sluja.scraper.shopscraper.connector.service.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.sluja.scraper.shopscraper.connector.dtos.request.ConnectRequest;
import org.sluja.scraper.shopscraper.connector.exceptions.connection.WebsiteConnectionTimeoutException;
import org.sluja.scraper.shopscraper.connector.exceptions.request.IncorrectConnectionRequestStructureException;
import org.sluja.scraper.shopscraper.connector.interfaces.IConnector;
import org.sluja.scraper.shopscraper.connector.interfaces.IWebsiteConnector;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebsiteScraperConnector implements IWebsiteConnector<Document> {

    private final ScraperConnector scraperConnector;
    public WebsiteScraperConnector() {
        this.scraperConnector = new ScraperConnector();
    }
    @Override
    public Document getWebpage(final String url) {
        try {
            final ConnectRequest request = new ConnectRequest(url);
            return scraperConnector.connect(request);
        } catch (final IncorrectConnectionRequestStructureException ex) {
            //TODO log
            //TODO throw ex
            return null;
        } catch (WebsiteConnectionTimeoutException e) {
            //TODO log
            //TODO throw ex
            return null;
        }
    }

    static class ScraperConnector implements IConnector<Document, ConnectRequest> {

        private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36";
        @Override
        public Document connect(ConnectRequest request) throws WebsiteConnectionTimeoutException {
            int tryCounter = 0;
            while (tryCounter < 5) {
                try {
                    return Jsoup.connect(request.url())
                            .userAgent(USER_AGENT)
                            .get();
                } catch (IOException ex) {
                    tryCounter++;
                }
            }
            throw new WebsiteConnectionTimeoutException();
        }
    }

}
