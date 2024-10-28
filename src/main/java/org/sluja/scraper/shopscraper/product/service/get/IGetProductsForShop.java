package org.sluja.scraper.shopscraper.product.service.get;

@FunctionalInterface
public interface IGetProductsForShop<T,R> {
    R get(final T request);
}
