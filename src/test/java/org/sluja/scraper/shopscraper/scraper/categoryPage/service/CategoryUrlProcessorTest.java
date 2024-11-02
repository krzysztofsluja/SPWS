package org.sluja.scraper.shopscraper.scraper.categoryPage.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.exceptions.EmptyCategoryPageElementsListException;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.service.CategoryUrlProcessor;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CategoryUrlProcessorTest {

    private CategoryUrlProcessor categoryUrlProcessor;
    private Document document;

    @BeforeEach
    void setUp() {
        categoryUrlProcessor = new CategoryUrlProcessor();
        document = Jsoup.parse("""
                <html>
                    <body>
                        <div class="category">
                            <a href="/category1">Category 1</a>
                            <a href="/category2 ">Category 2</a>
                            <a href=" /category3">Category 3</a>
                        </div>
                        <div class="subcategory">
                            <a href="/sub1">Sub 1</a>
                            <a href="/sub2">Sub 2</a>
                        </div>
                    </body>
                </html>
                """);
        document.setBaseUri("http://test.com");
    }

    @Nested
    @DisplayName("processUrls method tests")
    class ProcessUrlsTests {
        
        @Test
        @DisplayName("Should process URLs successfully with valid input")
        void shouldProcessUrlsSuccessfully() throws EmptyCategoryPageElementsListException {
            // Given
            List<String> properties = List.of("div.category a", "div.subcategory a");
            
            // When
            Set<String> result = categoryUrlProcessor.processUrls(properties, document, "href");
            
            // Then
            assertThat(result)
                    .hasSize(5)
                    .containsExactlyInAnyOrder(
                            "/category1",
                            "/category2",
                            "/category3",
                            "/sub1",
                            "/sub2"
                    );
        }

        @Test
        @DisplayName("Should throw EmptyCategoryPageElementsListException when no URLs found")
        void shouldThrowExceptionWhenNoUrlsFound() {
            // Given
            List<String> properties = List.of("div.nonexistent a");
            
            // When/Then
            assertThatThrownBy(() -> 
                categoryUrlProcessor.processUrls(properties, document, "href"))
                    .isInstanceOf(EmptyCategoryPageElementsListException.class);
        }

        @Test
        @DisplayName("Should remove duplicate URLs")
        void shouldRemoveDuplicateUrls() throws EmptyCategoryPageElementsListException {
            // Given
            Document docWithDuplicates = Jsoup.parse("""
                    <div class="test">
                        <a href="/url1">Link 1</a>
                        <a href="/url1">Link 1 Duplicate</a>
                    </div>
                    """);
            List<String> properties = List.of("div.test a");
            
            // When
            Set<String> result = categoryUrlProcessor.processUrls(properties, docWithDuplicates, "href");
            
            // Then
            assertThat(result)
                    .hasSize(1)
                    .containsExactly("/url1");
        }
    }

    @Nested
    @DisplayName("CategoryUrlCleaner tests")
    class CategoryUrlCleanerTests {
        
        @ParameterizedTest(name = "Should clean URL: {0} to: {1}")
        @MethodSource("provideUrlsForCleaning")
        @DisplayName("Should clean URLs correctly")
        void shouldCleanUrlsCorrectly(String input, String expected) {
            // When
            String result = CategoryUrlProcessor.CategoryUrlCleaner.cleanUrl(input);
            
            // Then
            assertThat(result).isEqualTo(expected);
        }

        private static Stream<Arguments> provideUrlsForCleaning() {
            return Stream.of(
                    Arguments.of("/url1 ", "/url1"),
                    Arguments.of(" /url2", "/url2"),
                    Arguments.of(" /url3 ", "/url3"),
                    Arguments.of("/url4", "/url4")
            );
        }
    }

    @Nested
    @DisplayName("CategoryUrlValidator tests")
    class CategoryUrlValidatorTests {

        @Test
        @DisplayName("Should throw exception for empty URL list")
        void shouldThrowExceptionForEmptyUrlList() {
            // Given
            List<String> urls = List.of();
            
            // When/Then
            assertThatThrownBy(() -> 
                CategoryUrlProcessor.CategoryUrlValidator.validateUrls(urls, "test"))
                    .isInstanceOf(EmptyCategoryPageElementsListException.class);
        }

        @Test
        @DisplayName("Should throw exception for null URL list")
        void shouldThrowExceptionForNullUrlList() {
            // When/Then
            assertThatThrownBy(() -> 
                CategoryUrlProcessor.CategoryUrlValidator.validateUrls(null, "test"))
                    .isInstanceOf(EmptyCategoryPageElementsListException.class);
        }
    }
} 