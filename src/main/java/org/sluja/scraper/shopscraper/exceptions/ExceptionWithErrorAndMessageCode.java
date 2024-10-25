package org.sluja.scraper.shopscraper.exceptions;

public class ExceptionWithErrorAndMessageCode extends Exception {

    private final Long errorCode;
    private final String messageCode;

    public ExceptionWithErrorAndMessageCode(final Long errorCode, final String messageCode) {
        super();
        this.errorCode = errorCode;
        this.messageCode = messageCode;
    }
}
