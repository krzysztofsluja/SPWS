package org.sluja.scraper.shopscraper.scraper.categoryPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sluja.scraper.shopscraper.connector.interfaces.IWebsiteConnector;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;
import org.sluja.scraper.shopscraper.scraper.categoryPage.dtos.AllCategoriesPageRequest;
import org.sluja.scraper.shopscraper.scraper.categoryPage.service.AllCategoriesPageScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AllCategoriesPageScraperTest {

    @Mock
    private IWebsiteConnector<Document> websiteScraperConnector;

    @Autowired
    private AllCategoriesPageScraper allCategoriesPageScraper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExtractPages() throws ExceptionWithErrorAndMessageCode {
        final String htmlSource = "<html><body>" +
                "<div class=\"category\">" +
                "  <a href=\"https://example.com/category1\" class=\"category-link\">Category 1</a>" +
                "</div>" +
                "<div class=\"category\">" +
                "  <a href=\"https://example.com/category2\" class=\"category-link\">Category 2</a>" +
                "</div>" +
                "<div class=\"category\">" +
                "  <a href=\"https://example.com/category3\" class=\"category-link\">Category 3</a>" +
                "</div>" +
                "</body></html>";

        final Document document = Jsoup.parse(htmlSource);
        when(websiteScraperConnector.getWebpage(anyString())).thenReturn(document);

        final AllCategoriesPageRequest request = new AllCategoriesPageRequest(
                "https://webscraper.io/test-sites/e-commerce/allinone",
                List.of("div.category > a"),
                "href",
                "example");

        final List<String> expected = List.of("https://example.com/category1", "https://example.com/category2", "https://example.com/category3");
        final List<String> actual = allCategoriesPageScraper.extractPages(document, request);

        assertEquals(expected, actual);
    }

    @Test
    public void testException() {
        final String emptyHtml = "<html><body></body></html>";
        final Document document = Jsoup.parse(emptyHtml);
        final AllCategoriesPageRequest request = new AllCategoriesPageRequest(
                "https://test.scrap",
                List.of("div.category > a"),
                "href",
                "example");
        final ExceptionWithErrorAndMessageCode exception = assertThrows(ExceptionWithErrorAndMessageCode.class, () -> allCategoriesPageScraper.getPages(request));
        assertTrue(exception.getErrorCode() != 0);
    }
}