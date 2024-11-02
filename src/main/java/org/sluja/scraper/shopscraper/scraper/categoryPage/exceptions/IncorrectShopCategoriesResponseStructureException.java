package org.sluja.scraper.shopscraper.scraper.categoryPage.exceptions;

import org.sluja.scraper.shopscraper.exceptions.RuntimeExceptionWithErrorAndMessageCode;

public class IncorrectShopCategoriesResponseStructureException extends RuntimeExceptionWithErrorAndMessageCode {

    public IncorrectShopCategoriesResponseStructureException() {
        super(3003L, "error.scraper.incorrect.shop.categories.response.structure");
    }
}
