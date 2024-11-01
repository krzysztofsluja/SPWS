package org.sluja.scraper.shopscraper.connector.dtos.request.crawler;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.sluja.scraper.shopscraper.connector.exceptions.request.IncorrectConnectionRequestStructureException;
import org.sluja.scraper.shopscraper.connector.utils.UrlValidator;

import java.time.Duration;
import java.util.Objects;

@Getter
public final class CrawlerConnectRequest implements AutoCloseable {

    private final String url;
    private final WebDriver driver;

    public CrawlerConnectRequest(final String url) {
        this(url, createChromeDriverWithCustomOptions());
    }

    private CrawlerConnectRequest(final String url, final WebDriver webDriver) {
        if (!UrlValidator.isValidHttpsUrl(url) || Objects.isNull(webDriver)) {
            throw new IncorrectConnectionRequestStructureException();
        }
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        this.url = url;
        this.driver = webDriver;
    }

    private static WebDriver createChromeDriverWithCustomOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        return new ChromeDriver(options);
    }

    @Override
    public void close() {
        driver.quit();
    }
}
