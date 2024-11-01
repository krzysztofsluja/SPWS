package org.sluja.scraper.shopscraper.connector.service.scraper;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.sluja.scraper.shopscraper.connector.dtos.request.ConnectRequest;
import org.sluja.scraper.shopscraper.connector.exceptions.connection.WebsiteConnectionTimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class ScraperConnectorTest {

    private final WebsiteScraperConnector.ScraperConnector scraperConnector = 
        new WebsiteScraperConnector.ScraperConnector();

    @Test
    void connect_ValidUrl_ReturnsDocument() throws Exception {
        // Arrange
        ConnectRequest request = new ConnectRequest("https://www.google.com");

        // Act
        Document result = scraperConnector.connect(request);

        // Assert
        assertNotNull(result);
        assertTrue(result.hasText());
    }

    @Test
    void connect_InvalidUrl_ThrowsException() {
        // Arrange
        ConnectRequest request = new ConnectRequest("https://invalid-url-that-does-not-exist.com");

        // Act & Assert
        assertThrows(WebsiteConnectionTimeoutException.class, () -> {
            scraperConnector.connect(request);
        });
    }
} 