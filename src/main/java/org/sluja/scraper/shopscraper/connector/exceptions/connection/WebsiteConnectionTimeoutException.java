package org.sluja.scraper.shopscraper.connector.exceptions.connection;

import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;

public class WebsiteConnectionTimeoutException extends ExceptionWithErrorAndMessageCode {

    public WebsiteConnectionTimeoutException() {
        super(2002L, "error.connector.website.connection.timeout");
    }
}
