package org.sluja.scraper.shopscraper.dtos.request;

import org.junit.jupiter.api.Test;
import org.sluja.scraper.shopscraper.dtos.request.attributes.ShopScrapingAttributes;
import org.sluja.scraper.shopscraper.exceptions.IncorrectGetProductsForShopRequestStructure;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetProductsForShopRequestTest {

    @Test
    void constructor_ValidInput_CreatesInstance() {
        // Arrange
        ShopCategoriesRequest shopRequest = new ShopCategoriesRequest("TestShop", Arrays.asList("Category1", "Category2"));
        Map<String, List<String>> properties = new HashMap<>();
        ShopScrapingAttributes attributes = new ShopScrapingAttributes(
            "priceSelector",           // productPrice
            "discountPriceSelector",   // productDiscountPrice
            "https://example.com",     // homePageUrl
            "descriptionSelector",     // productDescription
            "descriptionAttr",         // productDescriptionAttribute
            "nameSelector",            // productName
            "imageUrlSelector",        // productImageUrl
            "imageAttr",               // productImageAttribute
            "detailsUrlSelector",      // productDetailsUrl
            "detailsAttr",            // productDetailsAttribute
            "productContainer",        // productInstanceContainer
            "paginationSelector",      // categoryPagePagination
            Arrays.asList("url1", "url2"), // allCategoriesUrls
            "categoriesAttr",          // allCategoriesAttribute
            "categoryUrlAttr"          // categoryUrlAttribute
        );
        String context = "test-context";

        // Act
        GetProductsForShopRequest request = new GetProductsForShopRequest(
            shopRequest, properties, attributes, context, true
        );

        // Assert
        assertNotNull(request);
        assertEquals(shopRequest, request.shopWithCategories());
        assertEquals(properties, request.categoriesProperties());
        assertEquals(attributes, request.scrapingAttributes());
        assertEquals(context, request.context());
        assertTrue(request.dynamicWebsite());
    }

    @Test
    void constructor_NullScrapingAttributes_ThrowsException() {
        // Arrange
        ShopCategoriesRequest shopRequest = new ShopCategoriesRequest("TestShop", Arrays.asList("Category1"));
        Map<String, List<String>> properties = new HashMap<>();
        String context = "test-context";

        // Act & Assert
        assertThrows(IncorrectGetProductsForShopRequestStructure.class, () -> {
            new GetProductsForShopRequest(shopRequest, properties, null, context, true);
        });
    }

    @Test
    void constructor_EmptyContext_ThrowsException() {
        // Arrange
        ShopCategoriesRequest shopRequest = new ShopCategoriesRequest("TestShop", Arrays.asList("Category1"));
        Map<String, List<String>> properties = new HashMap<>();
        ShopScrapingAttributes attributes = new ShopScrapingAttributes(
            "priceSelector", "discountPriceSelector", "https://example.com",
            "descriptionSelector", "descriptionAttr", "nameSelector",
            "imageUrlSelector", "imageAttr", "detailsUrlSelector",
            "detailsAttr", "productContainer", "paginationSelector",
            Arrays.asList("url1", "url2"), "categoriesAttr", "categoryUrlAttr"
        );

        // Act & Assert
        assertThrows(IncorrectGetProductsForShopRequestStructure.class, () -> {
            new GetProductsForShopRequest(shopRequest, properties, attributes, "", true);
        });
    }

    @Test
    void constructor_AddsCategoriesToProperties() {
        // Arrange
        List<String> categories = Arrays.asList("Category1", "Category2");
        ShopCategoriesRequest shopRequest = new ShopCategoriesRequest("TestShop", categories);
        Map<String, List<String>> properties = new HashMap<>();
        ShopScrapingAttributes attributes = new ShopScrapingAttributes(
            "priceSelector", "discountPriceSelector", "https://example.com",
            "descriptionSelector", "descriptionAttr", "nameSelector",
            "imageUrlSelector", "imageAttr", "detailsUrlSelector",
            "detailsAttr", "productContainer", "paginationSelector",
            Arrays.asList("url1", "url2"), "categoriesAttr", "categoryUrlAttr"
        );
        String context = "test-context";

        // Act
        GetProductsForShopRequest request = new GetProductsForShopRequest(
            shopRequest, properties, attributes, context, false
        );

        // Assert
        for (String category : categories) {
            assertTrue(request.categoriesProperties().containsKey(category));
            assertTrue(request.categoriesProperties().get(category).contains(category));
        }
    }

    @Test
    void constructor_PreservesExistingProperties() {
        // Arrange
        List<String> categories = Arrays.asList("Category1");
        ShopCategoriesRequest shopRequest = new ShopCategoriesRequest("TestShop", categories);
        Map<String, List<String>> properties = new HashMap<>();
        properties.put("Category1", new ArrayList<>(List.of("ExistingProperty")));
        ShopScrapingAttributes attributes = new ShopScrapingAttributes(
            "priceSelector", "discountPriceSelector", "https://example.com",
            "descriptionSelector", "descriptionAttr", "nameSelector",
            "imageUrlSelector", "imageAttr", "detailsUrlSelector",
            "detailsAttr", "productContainer", "paginationSelector",
            Arrays.asList("url1", "url2"), "categoriesAttr", "categoryUrlAttr"
        );
        String context = "test-context";

        // Act
        GetProductsForShopRequest request = new GetProductsForShopRequest(
            shopRequest, properties, attributes, context, false
        );

        // Assert
        assertTrue(request.categoriesProperties().get("Category1").contains("ExistingProperty"));
        assertTrue(request.categoriesProperties().get("Category1").contains("Category1"));
        assertEquals(2, request.categoriesProperties().get("Category1").size());
    }
} 