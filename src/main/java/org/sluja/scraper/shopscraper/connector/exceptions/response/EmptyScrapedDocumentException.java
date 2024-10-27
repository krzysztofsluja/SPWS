package org.sluja.scraper.shopscraper.connector.exceptions.response;

import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;

public class EmptyScrapedDocumentException extends ExceptionWithErrorAndMessageCode {
    public EmptyScrapedDocumentException() {
        super(2003L, "error.connector.empty.scraped.document");
    }
}
