package org.sluja.scraper.shopscraper.exceptions;

public class RuntimeExceptionWithErrorAndMessageCode extends RuntimeException {

    private final Long errorCode;
    private final String messageCode;

    public RuntimeExceptionWithErrorAndMessageCode(final Long errorCode, final String messageCode) {
        super();
        this.errorCode = errorCode;
        this.messageCode = messageCode;
    }
}
