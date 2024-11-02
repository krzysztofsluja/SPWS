package org.sluja.scraper.shopscraper.dtos.response;

import org.junit.jupiter.api.Test;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.exceptions.IncorrectShopCategoriesResponseStructureException;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopCategoriesDataTest {

    @Test
    void constructor_ValidInput_CreatesInstance() {
        // Arrange
        String shopName = "TestShop";
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "url2"));
        categoryUrls.put("Category2", Arrays.asList("url3", "url4"));

        // Act
        ShopCategoriesData response = new ShopCategoriesData(shopName, categoryUrls);

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
            new ShopCategoriesData(null, categoryUrls);
        });
    }

    @Test
    void constructor_EmptyShopName_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesData("", categoryUrls);
        });
    }

    @Test
    void constructor_BlankShopName_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesData("   ", categoryUrls);
        });
    }

    @Test
    void constructor_NullCategoryUrls_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesData("TestShop", null);
        });
    }

    @Test
    void constructor_EmptyCategoryUrls_ThrowsException() {
        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesData("TestShop", Collections.emptyMap());
        });
    }

    @Test
    void constructor_BlankCategoryKey_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("   ", Arrays.asList("url1", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesData("TestShop", categoryUrls);
        });
    }

    @Test
    void constructor_EmptyUrlsList_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Collections.emptyList());

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesData("TestShop", categoryUrls);
        });
    }

    @Test
    void constructor_BlankUrl_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "   ", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesData("TestShop", categoryUrls);
        });
    }

    @Test
    void constructor_EmptyUrl_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesData("TestShop", categoryUrls);
        });
    }

    @Test
    void constructor_NullUrl_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", null, "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesData("TestShop", categoryUrls);
        });
    }

    @Test
    void constructor_NullCategoryKey_ThrowsException() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put(null, Arrays.asList("url1", "url2"));

        // Act & Assert
        assertThrows(IncorrectShopCategoriesResponseStructureException.class, () -> {
            new ShopCategoriesData("TestShop", categoryUrls);
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

        ShopCategoriesData response1 = new ShopCategoriesData("TestShop", categoryUrls1);
        ShopCategoriesData response2 = new ShopCategoriesData("TestShop", categoryUrls2);
        ShopCategoriesData response3 = new ShopCategoriesData("TestShop", categoryUrls3);

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

        ShopCategoriesData response1 = new ShopCategoriesData("TestShop", categoryUrls1);
        ShopCategoriesData response2 = new ShopCategoriesData("TestShop", categoryUrls2);

        // Assert
        assertEquals(response1.hashCode(), response2.hashCode());
    }

    @Test
    void record_ImplementsToStringCorrectly() {
        // Arrange
        Map<String, List<String>> categoryUrls = new HashMap<>();
        categoryUrls.put("Category1", Arrays.asList("url1", "url2"));
        ShopCategoriesData response = new ShopCategoriesData("TestShop", categoryUrls);

        // Act
        String toString = response.toString();

        // Assert
        assertTrue(toString.contains("TestShop"));
        assertTrue(toString.contains("Category1"));
        assertTrue(toString.contains("url1"));
        assertTrue(toString.contains("url2"));
    }
} 