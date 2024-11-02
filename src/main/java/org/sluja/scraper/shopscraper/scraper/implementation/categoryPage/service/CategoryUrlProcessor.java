package org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.exceptions.EmptyCategoryPageElementsListException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CategoryUrlProcessor {
    
    public Set<String> processUrls(final List<String> properties,
                                    final Document document,
                                    final String extractAttribute) throws EmptyCategoryPageElementsListException {
        final Set<String> processedUrls = properties.parallelStream()
                .map(property -> extractUrlsByProperty(document, property, extractAttribute))
                .flatMap(List::stream)
                .map(CategoryUrlCleaner::cleanUrl)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());
        CategoryUrlValidator.validateUrls(processedUrls, document.location());
        return processedUrls;
    }

    private List<String> extractUrlsByProperty(final Document document,
                                               final String property,
                                               final String extractAttribute) {
        try {
            final Elements elements = document.select(property);
            return elements.eachAttr(extractAttribute);
        } catch (final Exception e) {
            log.error("Error extracting URLs with property: {}", property, e);
            return Collections.emptyList();
        }
    }

    static class CategoryUrlValidator {
        public static void validateUrls(final Collection<String> urls,
                                        final String requestInfo) throws EmptyCategoryPageElementsListException {
            if (CollectionUtils.isEmpty(urls)) {
                log.warn("No category page URLs found for request: {}", requestInfo);
                throw new EmptyCategoryPageElementsListException();
            }
        }
    }

    static class CategoryUrlCleaner {
        public static String cleanUrl(final String url) {
            return url.replaceAll("\\s", StringUtils.EMPTY);
        }
    }
} 