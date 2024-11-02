package org.sluja.scraper.shopscraper.exceptions;

public class IncorrectShopCategoriesRequestStructureException extends RuntimeExceptionWithErrorAndMessageCode {
    public IncorrectShopCategoriesRequestStructureException() {
        super(4002L, "error.request.incorrect.shop.categories.request.structure");
    }
} 