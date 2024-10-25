package org.sluja.scraper.shopscraper.scraper.interfaces;

import org.sluja.scraper.shopscraper.scraper.dtos.request.ScrapRequest;

@FunctionalInterface
public interface WebsiteScraper<RTN,RQT extends ScrapRequest>{

    RTN scrap(RQT request);
}
