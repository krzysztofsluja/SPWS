package org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.exceptions;

import org.sluja.scraper.shopscraper.exceptions.RuntimeExceptionWithErrorAndMessageCode;

public class IncorrectCategoryPageRequestStructure extends RuntimeExceptionWithErrorAndMessageCode {
    public IncorrectCategoryPageRequestStructure() {
        super(3001L, "error.scraper.incorrect.category.page.request.structure");
    }
}
