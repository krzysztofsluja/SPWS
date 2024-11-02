package org.sluja.scraper.shopscraper.scraper.general.dtos.request;

import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.scraper.general.exceptions.request.IncorrectScrapRequestStructure;

public record ScrapRequest(String property) {

    public ScrapRequest {
        if (StringUtils.isBlank(property)) {
            throw new IncorrectScrapRequestStructure();
        }
    }
}
