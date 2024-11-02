package org.sluja.scraper.shopscraper.connector.utils;

import org.apache.commons.lang3.StringUtils;

public final class UrlValidator {
    
    private UrlValidator() {
        // Utility class should not be instantiated
    }

    /**
     * Validates if the URL is a valid HTTPS URL.
     * @param url URL to validate
     * @return true if URL is valid, false otherwise
     */
    public static boolean isValidHttpsUrl(final String url) {
        return !StringUtils.isBlank(url) && url.startsWith("https://");
    }
} 