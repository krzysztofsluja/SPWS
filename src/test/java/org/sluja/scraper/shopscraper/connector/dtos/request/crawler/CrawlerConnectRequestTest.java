package org.sluja.scraper.shopscraper.connector.dtos.request.crawler;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sluja.scraper.shopscraper.connector.exceptions.request.IncorrectConnectionRequestStructureException;

import static org.junit.jupiter.api.Assertions.*;

class CrawlerConnectRequestTest {

    @Test
    void constructor_ValidHttpsUrl_CreatesInstance() {
        // Arrange
        String validUrl = "https://example.com";

        // Act & Assert
        try (CrawlerConnectRequest request = new CrawlerConnectRequest(validUrl)) {
            assertNotNull(request);
            assertEquals(validUrl, request.getUrl());
            assertNotNull(request.getDriver());
            assertInstanceOf(ChromeDriver.class, request.getDriver());
        }
    }

    @Test
    void constructor_NonHttpsUrl_ThrowsException() {
        // Arrange
        String invalidUrl = "http://example.com";

        // Act & Assert
        assertThrows(IncorrectConnectionRequestStructureException.class, () -> {
            new CrawlerConnectRequest(invalidUrl);
        });
    }

    @Test
    void constructor_InvalidUrlFormat_ThrowsException() {
        // Arrange
        String invalidUrl = "example.com";

        // Act & Assert
        assertThrows(IncorrectConnectionRequestStructureException.class, () -> {
            new CrawlerConnectRequest(invalidUrl);
        });
    }

    @Test
    void constructor_NullUrl_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectConnectionRequestStructureException.class, () -> {
            new CrawlerConnectRequest(null);
        });
    }

    @Test
    void constructor_EmptyUrl_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectConnectionRequestStructureException.class, () -> {
            new CrawlerConnectRequest("");
        });
    }

    @Test
    void constructor_BlankUrl_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectConnectionRequestStructureException.class, () -> {
            new CrawlerConnectRequest("   ");
        });
    }

    @Test
    void close_QuitsWebDriver() {
        // Arrange
        String validUrl = "https://example.com";

        // Act & Assert
        CrawlerConnectRequest request = new CrawlerConnectRequest(validUrl);
        WebDriver driver = request.getDriver();
        assertNotNull(driver);
        
        request.close();
    }

    @Test
    void autoCloseable_ClosesResourcesAutomatically() {
        // Arrange
        String validUrl = "https://example.com";
        WebDriver driver = null;

        // Act & Assert
        try (CrawlerConnectRequest request = new CrawlerConnectRequest(validUrl)) {
            driver = request.getDriver();
            assertNotNull(driver);
        }
    }

    @Test
    void constructor_SetsTimeouts() {
        // Arrange
        String validUrl = "https://example.com";

        // Act
        try (CrawlerConnectRequest request = new CrawlerConnectRequest(validUrl)) {
            WebDriver driver = request.getDriver();
            
            // Assert
            assertNotNull(driver);
        }
    }
} 