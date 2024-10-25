package org.sluja.scraper.shopscraper.connector.dtos.request;

import org.sluja.scraper.shopscraper.connector.exceptions.request.IncorrectConnectionRequestStructureException;

public record ConnectRequest(String url) {
    public ConnectRequest {
        if (!url.startsWith("https://")) {
            throw new IncorrectConnectionRequestStructureException();
        }
    }
}

