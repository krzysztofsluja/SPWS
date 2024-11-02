package org.sluja.scraper.shopscraper.connector.dtos.request;

import org.junit.jupiter.api.Test;
import org.sluja.scraper.shopscraper.connector.exceptions.request.IncorrectConnectionRequestStructureException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectRequestTest {

    @Test
    void constructor_ValidHttpsUrl_CreatesInstance() {
        // Arrange
        String validUrl = "https://example.com";

        // Act
        ConnectRequest request = new ConnectRequest(validUrl);

        // Assert
        assertNotNull(request);
        assertEquals(validUrl, request.url());
    }

    @Test
    void constructor_NonHttpsUrl_ThrowsException() {
        // Arrange
        String invalidUrl = "http://example.com";

        // Act & Assert
        assertThrows(IncorrectConnectionRequestStructureException.class, () -> {
            new ConnectRequest(invalidUrl);
        });
    }

    @Test
    void constructor_InvalidUrlFormat_ThrowsException() {
        // Arrange
        String invalidUrl = "example.com";

        // Act & Assert
        assertThrows(IncorrectConnectionRequestStructureException.class, () -> {
            new ConnectRequest(invalidUrl);
        });
    }

    @Test
    void constructor_NullUrl_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectConnectionRequestStructureException.class, () -> {
            new ConnectRequest(null);
        });
    }

    @Test
    void constructor_EmptyUrl_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectConnectionRequestStructureException.class, () -> {
            new ConnectRequest("");
        });
    }

    @Test
    void constructor_BlankUrl_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectConnectionRequestStructureException.class, () -> {
            new ConnectRequest("   ");
        });
    }

    @Test
    void record_ImplementsEqualsCorrectly() {
        // Arrange
        ConnectRequest request1 = new ConnectRequest("https://example.com");
        ConnectRequest request2 = new ConnectRequest("https://example.com");
        ConnectRequest request3 = new ConnectRequest("https://different.com");

        // Assert
        assertEquals(request1, request2);
        assertNotEquals(request1, request3);
    }

    @Test
    void record_ImplementsHashCodeCorrectly() {
        // Arrange
        ConnectRequest request1 = new ConnectRequest("https://example.com");
        ConnectRequest request2 = new ConnectRequest("https://example.com");

        // Assert
        assertEquals(request1.hashCode(), request2.hashCode());
    }

    @Test
    void record_ImplementsToStringCorrectly() {
        // Arrange
        String url = "https://example.com";
        ConnectRequest request = new ConnectRequest(url);

        // Act
        String toString = request.toString();

        // Assert
        assertTrue(toString.contains(url));
    }
} 