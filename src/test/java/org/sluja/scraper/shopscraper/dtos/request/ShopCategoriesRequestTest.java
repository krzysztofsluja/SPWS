package org.sluja.scraper.shopscraper.dtos.request;

import org.junit.jupiter.api.Test;
import org.sluja.scraper.shopscraper.exceptions.IncorrectShopCategoriesRequestStructureException;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopCategoriesRequestTest {

    @Test
    void constructor_ValidInput_CreatesInstance() {
        // Arrange
        String shopName = "TestShop";
        List<String> categories = Arrays.asList("Category1", "Category2");

        // Act
        ShopCategoriesRequest request = new ShopCategoriesRequest(shopName, categories);

        // Assert
        assertNotNull(request);
        assertEquals(shopName, request.shopName());
        assertEquals(categories, request.categories());
    }

    @Test
    void constructor_NullShopName_ThrowsException() {
        // Arrange
        List<String> categories = Arrays.asList("Category1", "Category2");

        // Act & Assert
        assertThrows(IncorrectShopCategoriesRequestStructureException.class, () -> {
            new ShopCategoriesRequest(null, categories);
        });
    }

    @Test
    void constructor_EmptyShopName_ThrowsException() {
        // Arrange
        List<String> categories = Arrays.asList("Category1", "Category2");

        // Act & Assert
        assertThrows(IncorrectShopCategoriesRequestStructureException.class, () -> {
            new ShopCategoriesRequest("", categories);
        });
    }

    @Test
    void constructor_BlankShopName_ThrowsException() {
        // Arrange
        List<String> categories = Arrays.asList("Category1", "Category2");

        // Act & Assert
        assertThrows(IncorrectShopCategoriesRequestStructureException.class, () -> {
            new ShopCategoriesRequest("   ", categories);
        });
    }

    @Test
    void constructor_NullCategories_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectShopCategoriesRequestStructureException.class, () -> {
            new ShopCategoriesRequest("TestShop", null);
        });
    }

    @Test
    void constructor_EmptyCategories_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectShopCategoriesRequestStructureException.class, () -> {
            new ShopCategoriesRequest("TestShop", Collections.emptyList());
        });
    }

    @Test
    void constructor_CategoriesWithBlankValue_ThrowsException() {
        // Arrange
        List<String> categoriesWithBlank = Arrays.asList("Category1", "   ", "Category2");

        // Act & Assert
        assertThrows(IncorrectShopCategoriesRequestStructureException.class, () -> {
            new ShopCategoriesRequest("TestShop", categoriesWithBlank);
        });
    }

    @Test
    void constructor_CategoriesWithEmptyValue_ThrowsException() {
        // Arrange
        List<String> categoriesWithEmpty = Arrays.asList("Category1", "", "Category2");

        // Act & Assert
        assertThrows(IncorrectShopCategoriesRequestStructureException.class, () -> {
            new ShopCategoriesRequest("TestShop", categoriesWithEmpty);
        });
    }

    @Test
    void constructor_CategoriesWithNullValue_ThrowsException() {
        // Arrange
        List<String> categoriesWithNull = Arrays.asList("Category1", null, "Category2");

        // Act & Assert
        assertThrows(IncorrectShopCategoriesRequestStructureException.class, () -> {
            new ShopCategoriesRequest("TestShop", categoriesWithNull);
        });
    }

    @Test
    void record_ImplementsEqualsCorrectly() {
        // Arrange
        List<String> categories = Arrays.asList("Category1", "Category2");
        ShopCategoriesRequest request1 = new ShopCategoriesRequest("TestShop", categories);
        ShopCategoriesRequest request2 = new ShopCategoriesRequest("TestShop", categories);
        ShopCategoriesRequest request3 = new ShopCategoriesRequest("DifferentShop", categories);

        // Assert
        assertEquals(request1, request2);
        assertNotEquals(request1, request3);
    }

    @Test
    void record_ImplementsHashCodeCorrectly() {
        // Arrange
        List<String> categories = Arrays.asList("Category1", "Category2");
        ShopCategoriesRequest request1 = new ShopCategoriesRequest("TestShop", categories);
        ShopCategoriesRequest request2 = new ShopCategoriesRequest("TestShop", categories);

        // Assert
        assertEquals(request1.hashCode(), request2.hashCode());
    }

    @Test
    void record_ImplementsToStringCorrectly() {
        // Arrange
        List<String> categories = Arrays.asList("Category1", "Category2");
        ShopCategoriesRequest request = new ShopCategoriesRequest("TestShop", categories);

        // Act
        String toString = request.toString();

        // Assert
        assertTrue(toString.contains("TestShop"));
        assertTrue(toString.contains("Category1"));
        assertTrue(toString.contains("Category2"));
    }
} 