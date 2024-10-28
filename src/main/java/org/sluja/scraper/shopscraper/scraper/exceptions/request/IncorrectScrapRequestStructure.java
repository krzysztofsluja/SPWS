package org.sluja.scraper.shopscraper.scraper.exceptions.request;

import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;
import org.sluja.scraper.shopscraper.exceptions.RuntimeExceptionWithErrorAndMessageCode;

public class IncorrectScrapRequestStructure extends RuntimeExceptionWithErrorAndMessageCode {

    public IncorrectScrapRequestStructure() {
        super(1001L, "error.scraper.incorrect.scrap.request.structure");
    }
}
