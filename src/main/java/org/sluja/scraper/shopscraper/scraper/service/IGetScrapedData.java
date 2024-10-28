package org.sluja.scraper.shopscraper.scraper.service;

import org.sluja.scraper.shopscraper.dtos.request.GetProductsForShopRequest;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;

@FunctionalInterface
public interface IGetScrapedData<RTN> {

    RTN getScrapedData(final GetProductsForShopRequest request) throws ExceptionWithErrorAndMessageCode;
}
