package org.sluja.scraper.shopscraper.scraper.categoryPage.dtos.cache;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.sluja.scraper.shopscraper.scraper.exceptions.cache.IncorrectCacheObjectException;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.dtos.cache.CategoryPagesForShopCacheObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryPagesForShopCacheObjectTest {

    @Test
    void of_ValidInput_CreatesInstance() {
        // Arrange
        String shopName = "TestShop";
        String context = "test-context";
        List<String> categoryPages = Arrays.asList("page1", "page2");

        // Act
        CategoryPagesForShopCacheObject object = CategoryPagesForShopCacheObject.of(shopName, context, categoryPages);

        // Assert
        assertNotNull(object);
        assertEquals(shopName, object.getShopName());
        assertEquals(context, object.getContext());
        assertEquals(categoryPages, object.getCategoryPages());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void of_InvalidShopName_ThrowsException(String shopName) {
        // Arrange
        String context = "test-context";
        List<String> categoryPages = Arrays.asList("page1", "page2");

        // Act & Assert
        assertThrows(IncorrectCacheObjectException.class, () -> {
            CategoryPagesForShopCacheObject.of(shopName, context, categoryPages);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void of_InvalidContext_ThrowsException(String context) {
        // Arrange
        String shopName = "TestShop";
        List<String> categoryPages = Arrays.asList("page1", "page2");

        // Act & Assert
        assertThrows(IncorrectCacheObjectException.class, () -> {
            CategoryPagesForShopCacheObject.of(shopName, context, categoryPages);
        });
    }

    @Test
    void of_NullCategoryPages_ThrowsException() {
        // Arrange
        String shopName = "TestShop";
        String context = "test-context";

        // Act & Assert
        assertThrows(IncorrectCacheObjectException.class, () -> {
            CategoryPagesForShopCacheObject.of(shopName, context, null);
        });
    }

    @Test
    void of_EmptyCategoryPages_ThrowsException() {
        // Arrange
        String shopName = "TestShop";
        String context = "test-context";

        // Act & Assert
        assertThrows(IncorrectCacheObjectException.class, () -> {
            CategoryPagesForShopCacheObject.of(shopName, context, Collections.emptyList());
        });
    }

    @Test
    void of_CategoryPagesWithNullValue_ThrowsException() {
        // Arrange
        String shopName = "TestShop";
        String context = "test-context";
        List<String> categoryPages = Arrays.asList("page1", null, "page2");

        // Act & Assert
        assertThrows(IncorrectCacheObjectException.class, () -> {
            CategoryPagesForShopCacheObject.of(shopName, context, categoryPages);
        });
    }

    @Test
    void of_CategoryPagesWithEmptyValue_ThrowsException() {
        // Arrange
        String shopName = "TestShop";
        String context = "test-context";
        List<String> categoryPages = Arrays.asList("page1", "", "page2");

        // Act & Assert
        assertThrows(IncorrectCacheObjectException.class, () -> {
            CategoryPagesForShopCacheObject.of(shopName, context, categoryPages);
        });
    }

    @Test
    void of_CategoryPagesWithBlankValue_ThrowsException() {
        // Arrange
        String shopName = "TestShop";
        String context = "test-context";
        List<String> categoryPages = Arrays.asList("page1", "   ", "page2");

        // Act & Assert
        assertThrows(IncorrectCacheObjectException.class, () -> {
            CategoryPagesForShopCacheObject.of(shopName, context, categoryPages);
        });
    }

    @Test
    void equals_SameValues_ReturnsTrue() {
        // Arrange
        CategoryPagesForShopCacheObject object1 = CategoryPagesForShopCacheObject.of(
            "TestShop", "test-context", Arrays.asList("page1", "page2")
        );
        CategoryPagesForShopCacheObject object2 = CategoryPagesForShopCacheObject.of(
            "TestShop", "test-context", Arrays.asList("page1", "page2")
        );

        // Assert
        assertEquals(object1, object2);
        assertEquals(object1.hashCode(), object2.hashCode());
    }

    @Test
    void equals_DifferentShopName_ReturnsFalse() {
        // Arrange
        CategoryPagesForShopCacheObject object1 = CategoryPagesForShopCacheObject.of(
            "TestShop1", "test-context", Arrays.asList("page1", "page2")
        );
        CategoryPagesForShopCacheObject object2 = CategoryPagesForShopCacheObject.of(
            "TestShop2", "test-context", Arrays.asList("page1", "page2")
        );

        // Assert
        assertNotEquals(object1, object2);
    }

    @Test
    void equals_DifferentContext_ReturnsFalse() {
        // Arrange
        CategoryPagesForShopCacheObject object1 = CategoryPagesForShopCacheObject.of(
            "TestShop", "context1", Arrays.asList("page1", "page2")
        );
        CategoryPagesForShopCacheObject object2 = CategoryPagesForShopCacheObject.of(
            "TestShop", "context2", Arrays.asList("page1", "page2")
        );

        // Assert
        assertNotEquals(object1, object2);
    }

    @Test
    void equals_DifferentCategoryPages_ReturnsFalse() {
        // Arrange
        CategoryPagesForShopCacheObject object1 = CategoryPagesForShopCacheObject.of(
            "TestShop", "test-context", Arrays.asList("page1", "page2")
        );
        CategoryPagesForShopCacheObject object2 = CategoryPagesForShopCacheObject.of(
            "TestShop", "test-context", Arrays.asList("page3", "page4")
        );

        // Assert
        assertEquals(object1, object2);
    }
} 