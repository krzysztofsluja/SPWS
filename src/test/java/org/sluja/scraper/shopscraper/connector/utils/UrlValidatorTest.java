package org.sluja.scraper.shopscraper.connector.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UrlValidatorTest {

    @Test
    void isValidHttpsUrl_ValidHttpsUrl_ReturnsTrue() {
        assertTrue(UrlValidator.isValidHttpsUrl("https://example.com"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "http://example.com",
        "ftp://example.com",
        "example.com",
        "https:",
        "https:/",
        "   https://example.com"
    })
    void isValidHttpsUrl_InvalidUrls_ReturnsFalse(String url) {
        assertFalse(UrlValidator.isValidHttpsUrl(url));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void isValidHttpsUrl_BlankUrls_ReturnsFalse(String url) {
        assertFalse(UrlValidator.isValidHttpsUrl(url));
    }
} 