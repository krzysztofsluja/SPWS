package org.sluja.scraper.shopscraper.scraper.dtos.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.sluja.scraper.shopscraper.scraper.exceptions.request.IncorrectScrapRequestStructure;

import static org.junit.jupiter.api.Assertions.*;

class ScrapRequestTest {

    @Test
    void constructor_ValidProperty_CreatesInstance() {
        // Arrange
        String validProperty = "div.test-class";

        // Act
        ScrapRequest request = new ScrapRequest(validProperty);

        // Assert
        assertNotNull(request);
        assertEquals(validProperty, request.property());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void constructor_InvalidProperty_ThrowsException(String invalidProperty) {
        // Act & Assert
        assertThrows(IncorrectScrapRequestStructure.class, () -> {
            new ScrapRequest(invalidProperty);
        });
    }

    @Test
    void constructor_ComplexCssSelector_CreatesInstance() {
        // Arrange
        String complexSelector = "div.class1 > p.class2[attr=value]:nth-child(2)";

        // Act
        ScrapRequest request = new ScrapRequest(complexSelector);

        // Assert
        assertNotNull(request);
        assertEquals(complexSelector, request.property());
    }

    @Test
    void record_ImplementsEqualsCorrectly() {
        // Arrange
        ScrapRequest request1 = new ScrapRequest("div.test");
        ScrapRequest request2 = new ScrapRequest("div.test");
        ScrapRequest request3 = new ScrapRequest("div.different");

        // Assert
        assertEquals(request1, request2);
        assertNotEquals(request1, request3);
    }

    @Test
    void record_ImplementsHashCodeCorrectly() {
        // Arrange
        ScrapRequest request1 = new ScrapRequest("div.test");
        ScrapRequest request2 = new ScrapRequest("div.test");

        // Assert
        assertEquals(request1.hashCode(), request2.hashCode());
    }

    @Test
    void record_ImplementsToStringCorrectly() {
        // Arrange
        String property = "div.test";
        ScrapRequest request = new ScrapRequest(property);

        // Act
        String toString = request.toString();

        // Assert
        assertTrue(toString.contains(property));
    }
} 