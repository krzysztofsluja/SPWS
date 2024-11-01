package org.sluja.scraper.shopscraper.connector.service.scraper;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sluja.scraper.shopscraper.connector.dtos.request.ConnectRequest;
import org.sluja.scraper.shopscraper.connector.exceptions.connection.WebsiteConnectionTimeoutException;
import org.sluja.scraper.shopscraper.connector.exceptions.request.IncorrectConnectionRequestStructureException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WebsiteScraperConnectorTest {

    @InjectMocks
    private WebsiteScraperConnector websiteScraperConnector;

    @Mock
    private WebsiteScraperConnector.ScraperConnector scraperConnector;

    @Mock
    private Document mockDocument;

    @Test
    void getWebpage_ValidUrl_ReturnsDocument() throws Exception {
        // Arrange
        String validUrl = "https://example.com";
        when(scraperConnector.connect(any())).thenReturn(mockDocument);

        // Act
        Document result = websiteScraperConnector.getWebpage(validUrl);

        // Assert
        assertNotNull(result);
        assertEquals(mockDocument, result);
        verify(scraperConnector).connect(any());
    }

    @Test
    void getWebpage_InvalidUrl_ThrowsException() throws WebsiteConnectionTimeoutException {
        // Arrange
        String invalidUrl = "invalid-url";

        // Act & Assert
        assertThrows(IncorrectConnectionRequestStructureException.class, () -> {
            websiteScraperConnector.getWebpage(invalidUrl);
        });
        verify(scraperConnector, never()).connect(any());
    }
} 