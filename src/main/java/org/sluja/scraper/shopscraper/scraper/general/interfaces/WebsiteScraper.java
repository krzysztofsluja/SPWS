package org.sluja.scraper.shopscraper.scraper.general.interfaces;

import org.sluja.scraper.shopscraper.scraper.general.dtos.request.ScrapRequest;

@FunctionalInterface
public interface WebsiteScraper<RTN,RQT extends ScrapRequest>{

    RTN scrap(RQT request);
}
