package org.sluja.scraper.shopscraper.connector.exceptions.request;

import org.sluja.scraper.shopscraper.exceptions.RuntimeExceptionWithErrorAndMessageCode;

public class IncorrectConnectionRequestStructureException extends RuntimeExceptionWithErrorAndMessageCode {

    public IncorrectConnectionRequestStructureException() {
        super(2001L, "error.connector.incorrect.connection.request.structure");
    }
}
