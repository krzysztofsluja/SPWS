package org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.sluja.scraper.shopscraper.dtos.request.GetProductsForShopRequest;
import org.sluja.scraper.shopscraper.dtos.response.ShopCategoriesResponse;
import org.sluja.scraper.shopscraper.exceptions.ExceptionWithErrorAndMessageCode;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.exceptions.EmptyCategoriesUrlsForShopException;
import org.sluja.scraper.shopscraper.scraper.general.service.IGetScrapedData;
import org.springframework.stereotype.Service;

import jakarta.annotation.PreDestroy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetShopCategoriesUrlsService implements IGetScrapedData<ShopCategoriesResponse> {
    
    private final ScrapeAllCategoriesPagesService scrapeAllCategoriesPagesService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final Map<String, Trie<String, Boolean>> categoryTries = new ConcurrentHashMap<>();

    @Override
    public ShopCategoriesResponse getScrapedData(final GetProductsForShopRequest shopRequest) 
            throws ExceptionWithErrorAndMessageCode {
        
        log.info("Starting URL search for shop: {} with {} categories: {}",
                shopRequest.shopWithCategories().shopName(),
                shopRequest.shopWithCategories().categories().size(),
                shopRequest.categoriesProperties().keySet());
        
        final Map<String, String> allPageUrls = scrapeAllCategoriesPagesService.getScrapedData(shopRequest);
        
        try {
            final Map<String, List<String>> categoryUrls = shopRequest.shopWithCategories().categories().stream()
                    .map(category -> CompletableFuture.supplyAsync(
                            () -> Map.entry(category, findMatchingUrls(category, allPageUrls, shopRequest.categoriesProperties().get(category))),
                            executorService
                    ))
                    .toList()
                    .stream()
                    .map(CompletableFuture::join)
                    .filter(entry -> CollectionUtils.isNotEmpty(entry.getValue()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue
                    ));

            log.info("Successfully processed all categories for shop: {}", 
                    shopRequest.shopWithCategories().shopName());

            if(MapUtils.isEmpty(categoryUrls)) {
                throw new EmptyCategoriesUrlsForShopException();
            }
            
            return new ShopCategoriesResponse(
                    shopRequest.shopWithCategories().shopName(), 
                    categoryUrls
            );
        } catch (Exception e) {
            log.error("Error processing categories for shop: {}. Error: {}", 
                    shopRequest.shopWithCategories().shopName(), 
                    e.getMessage());
            throw e;
        }
    }

 private List<String> findMatchingUrls(final String category,
                                       final Map<String, String> allPageUrls,
                                       final List<String> categoryProperties) {
        categoryTries.computeIfAbsent(category, _ -> {
            PatriciaTrie<Boolean> trie = new PatriciaTrie<>();
            categoryProperties.forEach(prop -> trie.put(prop.toLowerCase(), Boolean.TRUE));
            return trie;
        });
        return allPageUrls.entrySet().stream()
                .filter(entry -> categoryProperties.stream()
                        .anyMatch(prop -> entry.getKey().toLowerCase().contains(prop.toLowerCase())))
                .map(Map.Entry::getValue)
                .toList();
    }

    @PreDestroy
    public void shutdown() {
        log.info("Shutting down executor service");
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                log.warn("Executor service did not terminate in time, forcing shutdown");
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            log.error("Executor service shutdown interrupted", e);
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
} 