package org.sluja.scraper.shopscraper.dtos.response;

import org.junit.jupiter.api.Test;
import org.sluja.scraper.shopscraper.scraper.categoryPage.exceptions.IncorrectShopCategoriesResponseStructureException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShopCategoriesResponseTest {

    @Test
    void constructor_ValidInput_CreatesInstance() {
        // Arrange
        String shopName = "TestShop";
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "url2"));
        categoryUrls.put("Category2", Arrays.asList("url3", "url4"));

        // Act
        ShopCategoriesResponse response = new ShopCategoriesResponse(shopName, categoryUrls);

        // Assert
        assertNotNull(response);
        assertEquals(shopName, response.shopName());
        assertEquals(categoryUrls, response.categoryUrls());
    }

    @Test
    void constructor_NullShopName_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesResponse(null, categoryUrls);
        });
    }

    @Test
    void constructor_EmptyShopName_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesResponse("", categoryUrls);
        });
    }

    @Test
    void constructor_BlankShopName_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesResponse("   ", categoryUrls);
        });
    }

    @Test
    void constructor_NullCategoryUrls_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesResponse("TestShop", null);
        });
    }

    @Test
    void constructor_EmptyCategoryUrls_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesResponse("TestShop", Collections.emptyMap());
        });
    }

    @Test
    void constructor_BlankCategoryKey_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("   ", Arrays.asList("url1", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesResponse("TestShop", categoryUrls);
        });
    }

    @Test
    void constructor_EmptyUrlsList_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Collections.emptyList());

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesResponse("TestShop", categoryUrls);
        });
    }

    @Test
    void constructor_BlankUrl_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "   ", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesResponse("TestShop", categoryUrls);
        });
    }

    @Test
    void constructor_EmptyUrl_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesResponse("TestShop", categoryUrls);
        });
    }

    @Test
    void constructor_NullUrl_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", null, "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesResponse("TestShop", categoryUrls);
        });
    }

    @Test
    void constructor_NullCategoryKey_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put(null, Arrays.asList("url1", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesResponse("TestShop", categoryUrls);
        });
    }

    @Test
    void record_ImplementsEqualsCorrectly() {
        // Arrange
        Map<String, List<String>> categoryUrls1 = new HashMap<>();
        categoryUrls1.put("Category1", Arrays.asList("url1", "url2"));
        
        Map<String, List<String>> categoryUrls2 = new HashMap<>();
        categoryUrls2.put("Category1", Arrays.asList("url1", "url2"));
        
        Map<String, List<String>> categoryUrls3 = new HashMap<>();
        categoryUrls3.put("Category2", Arrays.asList("url3", "url4"));

        ShopCategoriesResponse response1 = new ShopCategoriesResponse("TestShop", categoryUrls1);
        ShopCategoriesResponse response2 = new ShopCategoriesResponse("TestShop", categoryUrls2);
        ShopCategoriesResponse response3 = new ShopCategoriesResponse("TestShop", categoryUrls3);

        // Assert
        assertEquals(response1, response2);
        assertNotEquals(response1, response3);
    }

    @Test
    void record_ImplementsHashCodeCorrectly() {
        // Arrange
        Map<String, List<String>> categoryUrls1 = new HashMap<>();
        categoryUrls1.put("Category1", Arrays.asList("url1", "url2"));
        
        Map<String, List<String>> categoryUrls2 = new HashMap<>();
        categoryUrls2.put("Category1", Arrays.asList("url1", "url2"));

        ShopCategoriesResponse response1 = new ShopCategoriesResponse("TestShop", categoryUrls1);
        ShopCategoriesResponse response2 = new ShopCategoriesResponse("TestShop", categoryUrls2);

        // Assert
        assertEquals(response1.hashCode(), response2.hashCode());
    }

    @Test
    void record_ImplementsToStringCorrectly() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "url2"));
        ShopCategoriesResponse response = new ShopCategoriesResponse("TestShop", categoryUrls);

        // Act
        String toString = response.toString();

        // Assert
        assertTrue(toString.contains("TestShop"));
        assertTrue(toString.contains("Category1"));
        assertTrue(toString.contains("url1"));
        assertTrue(toString.contains("url2"));
    }
} 