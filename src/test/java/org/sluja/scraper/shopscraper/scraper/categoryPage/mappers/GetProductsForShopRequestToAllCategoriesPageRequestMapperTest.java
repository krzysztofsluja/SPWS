package org.sluja.scraper.shopscraper.scraper.categoryPage.mappers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.sluja.scraper.shopscraper.dtos.request.GetProductsForShopRequest;
import org.sluja.scraper.shopscraper.dtos.request.ShopCategoriesRequest;
import org.sluja.scraper.shopscraper.dtos.request.attributes.ShopScrapingAttributes;
import org.sluja.scraper.shopscraper.scraper.categoryPage.dtos.AllCategoriesPageRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetProductsForShopRequestToAllCategoriesPageRequestMapperTest {

    @Autowired
    private GetProductsForShopRequestToAllCategoriesPageRequestMapper mapper;

    @Test
    void map_ValidInput_MapsCorrectly() {
        // Arrange
        String shopName = "TestShop";
        String homePageUrl = "https://example.com";
        List<String> allCategoriesUrls = Arrays.asList("selector1", "selector2");
        String allCategoriesAttribute = "category-attr";

        ShopCategoriesRequest shopRequest = new ShopCategoriesRequest(shopName, Arrays.asList("Category1", "Category2"));
        
        ShopScrapingAttributes attributes = new ShopScrapingAttributes(
            "priceSelector",           // productPrice
            "discountPriceSelector",   // productDiscountPrice
            homePageUrl,               // homePageUrl
            "descriptionSelector",     // productDescription
            "descriptionAttr",         // productDescriptionAttribute
            "nameSelector",            // productName
            "imageUrlSelector",        // productImageUrl
            "imageAttr",               // productImageAttribute
            "detailsUrlSelector",      // productDetailsUrl
            "detailsAttr",            // productDetailsAttribute
            "productContainer",        // productInstanceContainer
            "paginationSelector",      // categoryPagePagination
            allCategoriesUrls,         // allCategoriesUrls
            allCategoriesAttribute,    // allCategoriesAttribute
            "categoryUrlAttr"          // categoryUrlAttribute
        );

        GetProductsForShopRequest source = new GetProductsForShopRequest(
            shopRequest,
            new HashMap<>(),
            attributes,
            "test-context",
            false
        );

        // Act
        AllCategoriesPageRequest result = mapper.map(source);

        // Assert
        assertNotNull(result);
        assertEquals(homePageUrl, result.mainPageUrl());
        assertEquals(allCategoriesUrls, result.allCategoriesPageAttribute());
        assertEquals(allCategoriesAttribute, result.pageUrlExtractAttribute());
        assertEquals(shopName, result.shopName());
    }

    @Test
    void map_NullSource_ReturnsNull() {
        // Act
        AllCategoriesPageRequest result = mapper.map(null);

        // Assert
        assertNull(result);
    }

    @Test
    void map_PreservesAllAttributes() {
        // Arrange
        String shopName = "TestShop";
        String homePageUrl = "https://example.com";
        List<String> allCategoriesUrls = Arrays.asList("selector1", "selector2", "selector3");
        String allCategoriesAttribute = "category-attr";

        ShopCategoriesRequest shopRequest = new ShopCategoriesRequest(shopName, Arrays.asList("Category1", "Category2"));
        
        ShopScrapingAttributes attributes = new ShopScrapingAttributes(
            "priceSelector",
            "discountPriceSelector",
            homePageUrl,
            "descriptionSelector",
            "descriptionAttr",
            "nameSelector",
            "imageUrlSelector",
            "imageAttr",
            "detailsUrlSelector",
            "detailsAttr",
            "productContainer",
            "paginationSelector",
            allCategoriesUrls,
            allCategoriesAttribute,
            "categoryUrlAttr"
        );

        GetProductsForShopRequest source = new GetProductsForShopRequest(
            shopRequest,
            new HashMap<>(),
            attributes,
            "test-context",
            false
        );

        // Act
        AllCategoriesPageRequest result = mapper.map(source);

        // Assert
        assertNotNull(result);
        // Verify all mapped fields
        assertEquals(homePageUrl, result.mainPageUrl());
        assertEquals(allCategoriesUrls, result.allCategoriesPageAttribute());
        assertEquals(allCategoriesAttribute, result.pageUrlExtractAttribute());
        assertEquals(shopName, result.shopName());
        
        // Verify list contents are preserved
        assertEquals(allCategoriesUrls.size(), result.allCategoriesPageAttribute().size());
        assertTrue(result.allCategoriesPageAttribute().containsAll(allCategoriesUrls));
    }

    @Test
    void map_HandlesEmptyLists() {
        // Arrange
        String shopName = "TestShop";
        String homePageUrl = "https://example.com";
        List<String> allCategoriesUrls = List.of(); // Empty list
        String allCategoriesAttribute = "category-attr";

        ShopCategoriesRequest shopRequest = new ShopCategoriesRequest(shopName, List.of("Category1"));
        
        ShopScrapingAttributes attributes = new ShopScrapingAttributes(
            "priceSelector",
            "discountPriceSelector",
            homePageUrl,
            "descriptionSelector",
            "descriptionAttr",
            "nameSelector",
            "imageUrlSelector",
            "imageAttr",
            "detailsUrlSelector",
            "detailsAttr",
            "productContainer",
            "paginationSelector",
            allCategoriesUrls,
            allCategoriesAttribute,
            "categoryUrlAttr"
        );

        GetProductsForShopRequest source = new GetProductsForShopRequest(
            shopRequest,
            new HashMap<>(),
            attributes,
            "test-context",
            false
        );

        // Act & Assert
        assertThrows(RuntimeException.class, () -> mapper.map(source));
    }
} 