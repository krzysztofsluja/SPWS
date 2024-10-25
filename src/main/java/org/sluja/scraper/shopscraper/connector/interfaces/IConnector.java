package org.sluja.scraper.shopscraper.connector.interfaces;

import org.sluja.scraper.shopscraper.connector.exceptions.connection.WebsiteConnectionTimeoutException;

@FunctionalInterface
public interface IConnector<RTN, RQT> {

    RTN connect(RQT request) throws WebsiteConnectionTimeoutException;
}
