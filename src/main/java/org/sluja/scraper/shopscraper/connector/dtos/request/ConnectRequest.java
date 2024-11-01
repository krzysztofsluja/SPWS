package org.sluja.scraper.shopscraper.connector.dtos.request;

import org.sluja.scraper.shopscraper.connector.exceptions.request.IncorrectConnectionRequestStructureException;
import org.sluja.scraper.shopscraper.connector.utils.UrlValidator;

public record ConnectRequest(String url) {
    public ConnectRequest {
        if (!UrlValidator.isValidHttpsUrl(url)) {
            throw new IncorrectConnectionRequestStructureException();
        }
    }
}

