package org.sluja.scraper.shopscraper.scraper.dtos.request;

import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.scraper.exceptions.request.IncorrectScrapRequestStructure;

public record ScrapRequest(String property) {

    public ScrapRequest {
        if (StringUtils.isEmpty(property)) {
            throw new IncorrectScrapRequestStructure();
        }
    }
}
