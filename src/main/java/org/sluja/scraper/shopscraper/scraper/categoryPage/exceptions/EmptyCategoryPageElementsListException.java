package org.sluja.scraper.shopscraper.scraper.categoryPage.exceptions;

import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;

public class EmptyCategoryPageElementsListException extends ExceptionWithErrorAndMessageCode {

    public EmptyCategoryPageElementsListException() {
        super(3002L, "error.scraper.category.page.empty.elements.list");
    }
}
