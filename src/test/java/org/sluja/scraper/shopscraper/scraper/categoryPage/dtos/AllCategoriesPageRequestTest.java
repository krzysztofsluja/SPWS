package org.sluja.scraper.shopscraper.scraper.categoryPage.dtos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.exceptions.IncorrectCategoryPageRequestStructure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllCategoriesPageRequestTest {

    @Test
    void constructor_ValidInput_CreatesInstance() {
        // Arrange
        String mainPageUrl = "https://example.com";
        List<String> attributes = Arrays.asList("attr1", "attr2");
        String extractAttribute = "extract-attr";
        String shopName = "TestShop";

        // Act
        AllCategoriesPageRequest request = new AllCategoriesPageRequest(
            mainPageUrl, attributes, extractAttribute, shopName
        );

        // Assert
        assertNotNull(request);
        assertEquals(mainPageUrl, request.mainPageUrl());
        assertEquals(attributes, request.allCategoriesPageAttribute());
        assertEquals(extractAttribute, request.pageUrlExtractAttribute());
        assertEquals(shopName, request.shopName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void constructor_InvalidMainPageUrl_ThrowsException(String mainPageUrl) {
        // Arrange
        List<String> attributes = Arrays.asList("attr1", "attr2");
        String extractAttribute = "extract-attr";
        String shopName = "TestShop";

        // Act & Assert
        assertThrows(IncorrectCategoryPageRequestStructure.class, () -> {
            new AllCategoriesPageRequest(mainPageUrl, attributes, extractAttribute, shopName);
        });
    }

    @Test
    void constructor_NullAttributes_ThrowsException() {
        // Arrange
        String mainPageUrl = "https://example.com";
        String extractAttribute = "extract-attr";
        String shopName = "TestShop";

        // Act & Assert
        assertThrows(IncorrectCategoryPageRequestStructure.class, () -> {
            new AllCategoriesPageRequest(mainPageUrl, null, extractAttribute, shopName);
        });
    }

    @Test
    void constructor_EmptyAttributes_ThrowsException() {
        // Arrange
        String mainPageUrl = "https://example.com";
        String extractAttribute = "extract-attr";
        String shopName = "TestShop";

        // Act & Assert
        assertThrows(IncorrectCategoryPageRequestStructure.class, () -> {
            new AllCategoriesPageRequest(mainPageUrl, Collections.emptyList(), extractAttribute, shopName);
        });
    }

    @Test
    void constructor_AttributesWithNullValue_ThrowsException() {
        // Arrange
        String mainPageUrl = "https://example.com";
        List<String> attributes = Arrays.asList("attr1", null, "attr2");
        String extractAttribute = "extract-attr";
        String shopName = "TestShop";

        // Act & Assert
        assertThrows(IncorrectCategoryPageRequestStructure.class, () -> {
            new AllCategoriesPageRequest(mainPageUrl, attributes, extractAttribute, shopName);
        });
    }

    @Test
    void constructor_AttributesWithEmptyValue_ThrowsException() {
        // Arrange
        String mainPageUrl = "https://example.com";
        List<String> attributes = Arrays.asList("attr1", "", "attr2");
        String extractAttribute = "extract-attr";
        String shopName = "TestShop";

        // Act & Assert
        assertThrows(IncorrectCategoryPageRequestStructure.class, () -> {
            new AllCategoriesPageRequest(mainPageUrl, attributes, extractAttribute, shopName);
        });
    }

    @Test
    void constructor_AttributesWithBlankValue_ThrowsException() {
        // Arrange
        String mainPageUrl = "https://example.com";
        List<String> attributes = Arrays.asList("attr1", "   ", "attr2");
        String extractAttribute = "extract-attr";
        String shopName = "TestShop";

        // Act & Assert
        assertThrows(IncorrectCategoryPageRequestStructure.class, () -> {
            new AllCategoriesPageRequest(mainPageUrl, attributes, extractAttribute, shopName);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void constructor_InvalidExtractAttribute_ThrowsException(String extractAttribute) {
        // Arrange
        String mainPageUrl = "https://example.com";
        List<String> attributes = Arrays.asList("attr1", "attr2");
        String shopName = "TestShop";

        // Act & Assert
        assertThrows(IncorrectCategoryPageRequestStructure.class, () -> {
            new AllCategoriesPageRequest(mainPageUrl, attributes, extractAttribute, shopName);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void constructor_InvalidShopName_ThrowsException(String shopName) {
        // Arrange
        String mainPageUrl = "https://example.com";
        List<String> attributes = Arrays.asList("attr1", "attr2");
        String extractAttribute = "extract-attr";

        // Act & Assert
        assertThrows(IncorrectCategoryPageRequestStructure.class, () -> {
            new AllCategoriesPageRequest(mainPageUrl, attributes, extractAttribute, shopName);
        });
    }

    @Test
    void record_ImplementsEqualsCorrectly() {
        // Arrange
        AllCategoriesPageRequest request1 = new AllCategoriesPageRequest(
            "https://example.com",
            Arrays.asList("attr1", "attr2"),
            "extract-attr",
            "TestShop"
        );
        AllCategoriesPageRequest request2 = new AllCategoriesPageRequest(
            "https://example.com",
            Arrays.asList("attr1", "attr2"),
            "extract-attr",
            "TestShop"
        );
        AllCategoriesPageRequest request3 = new AllCategoriesPageRequest(
            "https://different.com",
            Arrays.asList("attr1", "attr2"),
            "extract-attr",
            "TestShop"
        );

        // Assert
        assertEquals(request1, request2);
        assertNotEquals(request1, request3);
    }

    @Test
    void record_ImplementsHashCodeCorrectly() {
        // Arrange
        AllCategoriesPageRequest request1 = new AllCategoriesPageRequest(
            "https://example.com",
            Arrays.asList("attr1", "attr2"),
            "extract-attr",
            "TestShop"
        );
        AllCategoriesPageRequest request2 = new AllCategoriesPageRequest(
            "https://example.com",
            Arrays.asList("attr1", "attr2"),
            "extract-attr",
            "TestShop"
        );

        // Assert
        assertEquals(request1.hashCode(), request2.hashCode());
    }

    @Test
    void record_ImplementsToStringCorrectly() {
        // Arrange
        String mainPageUrl = "https://example.com";
        List<String> attributes = Arrays.asList("attr1", "attr2");
        String extractAttribute = "extract-attr";
        String shopName = "TestShop";

        AllCategoriesPageRequest request = new AllCategoriesPageRequest(
            mainPageUrl, attributes, extractAttribute, shopName
        );

        // Act
        String toString = request.toString();

        // Assert
        assertTrue(toString.contains(mainPageUrl));
        assertTrue(toString.contains("attr1"));
        assertTrue(toString.contains("attr2"));
        assertTrue(toString.contains(extractAttribute));
        assertTrue(toString.contains(shopName));
    }
} 