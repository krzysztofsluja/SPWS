package org.sluja.scraper.shopscraper.scraper.categoryPage.exceptions;

import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;

public class EmptyCategoriesUrlsForShopException extends ExceptionWithErrorAndMessageCode {
    public EmptyCategoriesUrlsForShopException() {
        super(3004L, "error.category.page.empty.categories.urls.for.shop");
    }
}
