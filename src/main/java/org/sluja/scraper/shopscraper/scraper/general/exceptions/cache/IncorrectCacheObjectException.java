package org.sluja.scraper.shopscraper.scraper.general.exceptions.cache;

import org.sluja.scraper.shopscraper.exceptions.RuntimeExceptionWithErrorAndMessageCode;

public class IncorrectCacheObjectException extends RuntimeExceptionWithErrorAndMessageCode {
    public IncorrectCacheObjectException() {
        super(1002L, "error.cache..object.incorrect.structure");
    }
}
