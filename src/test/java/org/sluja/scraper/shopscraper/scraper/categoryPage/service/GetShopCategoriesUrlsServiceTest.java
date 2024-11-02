package org.sluja.scraper.shopscraper.scraper.categoryPage.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sluja.scraper.shopscraper.dtos.request.GetProductsForShopRequest;
import org.sluja.scraper.shopscraper.dtos.request.ShopCategoriesRequest;
import org.sluja.scraper.shopscraper.dtos.request.attributes.ShopScrapingAttributes;
import org.sluja.scraper.shopscraper.dtos.response.ShopCategoriesResponse;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.exceptions.EmptyCategoriesUrlsForShopException;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.service.GetShopCategoriesUrlsService;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.service.ScrapeAllCategoriesPagesService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetShopCategoriesUrlsServiceTest {

    @Mock
    private ScrapeAllCategoriesPagesService scrapeAllCategoriesPagesService;

    @InjectMocks
    private GetShopCategoriesUrlsService service;

    private GetProductsForShopRequest request;
    private Map<String, String> mockPageUrls;

    @BeforeEach
    void setUp() {
        String shopName = "TestShop";
        List<String> categories = List.of("Category1", "Category2");
        ShopCategoriesRequest shopRequest = new ShopCategoriesRequest(shopName, categories);

        ShopScrapingAttributes attributes = new ShopScrapingAttributes(
            "priceSelector", "discountSelector", "https://example.com",
            "descSelector", "descAttr", "nameSelector",
            "imageSelector", "imageAttr", "detailsSelector",
            "detailsAttr", "containerSelector", "paginationSelector",
            List.of("selector1", "selector2"), "categoryAttr", "urlAttr"
        );

        Map<String, List<String>> properties = new HashMap<>();
        properties.put("Category1", new ArrayList<>(List.of("cat1", "category1")));
        properties.put("Category2", new ArrayList<>(List.of("cat2", "category2")));

        request = new GetProductsForShopRequest(shopRequest, properties, attributes, "test-context", false);

        mockPageUrls = new HashMap<>();
        mockPageUrls.put("category1", "https://example.com/category1");
        mockPageUrls.put("category2", "https://example.com/category2");
    }

    @Test
    void getScrapedData_ValidRequest_ReturnsResponse() throws Exception {
        // Arrange
        when(scrapeAllCategoriesPagesService.getScrapedData(any())).thenReturn(mockPageUrls);

        // Act
        ShopCategoriesResponse response = service.getScrapedData(request);

        // Assert
        assertNotNull(response);
        assertEquals(request.shopWithCategories().shopName(), response.shopName());
        assertFalse(response.categoryUrls().isEmpty());
        verify(scrapeAllCategoriesPagesService).getScrapedData(request);
    }

    @Test
    void getScrapedData_NoMatchingUrls_ThrowsException() throws Exception {
        // Arrange
        Map<String, String> emptyUrls = new HashMap<>();
        when(scrapeAllCategoriesPagesService.getScrapedData(any())).thenReturn(emptyUrls);

        // Act & Assert
        assertThrows(EmptyCategoriesUrlsForShopException.class, () -> {
            service.getScrapedData(request);
        });
        verify(scrapeAllCategoriesPagesService).getScrapedData(request);
    }

    @Test
    void getScrapedData_ServiceThrowsException_PropagatesException() throws Exception {
        // Arrange
        when(scrapeAllCategoriesPagesService.getScrapedData(any()))
            .thenThrow(new RuntimeException("Test exception"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            service.getScrapedData(request);
        });
        verify(scrapeAllCategoriesPagesService).getScrapedData(request);
    }

    @Test
    void getScrapedData_MatchesUrlsCorrectly() throws Exception {
        // Arrange
        when(scrapeAllCategoriesPagesService.getScrapedData(any())).thenReturn(mockPageUrls);

        // Act
        ShopCategoriesResponse response = service.getScrapedData(request);

        // Assert
        assertNotNull(response);
        assertTrue(response.categoryUrls().containsKey("Category1"));
        assertTrue(response.categoryUrls().get("Category1").contains("https://example.com/category1"));
        verify(scrapeAllCategoriesPagesService).getScrapedData(request);
    }

    @Test
    void getScrapedData_HandlesMultipleUrlsPerCategory() throws Exception {
        // Arrange
        mockPageUrls.put("another category1 page", "https://example.com/category1/subcategory");
        when(scrapeAllCategoriesPagesService.getScrapedData(any())).thenReturn(mockPageUrls);

        // Act
        ShopCategoriesResponse response = service.getScrapedData(request);

        // Assert
        assertNotNull(response);
        assertTrue(response.categoryUrls().containsKey("Category1"));
        assertEquals(2, response.categoryUrls().get("Category1").size());
        verify(scrapeAllCategoriesPagesService).getScrapedData(request);
    }
} 