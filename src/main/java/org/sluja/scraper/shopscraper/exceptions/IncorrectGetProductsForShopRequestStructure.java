package org.sluja.scraper.shopscraper.exceptions;

public class IncorrectGetProductsForShopRequestStructure extends RuntimeExceptionWithErrorAndMessageCode {
    public IncorrectGetProductsForShopRequestStructure() {
        super(4001L, "error.request.incorrect.get.products.for.shop.request.structure");
    }
}
