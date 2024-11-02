package org.sluja.scraper.shopscraper.config.cache;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.config.cache.CategoryPagesForShopKeyGenerator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableCaching
public class ApplicationCacheManagerConfiguration {

    private final Cache<Object, Object> categoryPagesForShopCache;
    @Bean
    public CacheManager cacheManager() {
        final CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.registerCustomCache("categoryPagesForShop", categoryPagesForShopCache);
        return cacheManager;
    }

    @Bean("categoryPagesForShopKeyGenerator")
    public KeyGenerator categoryPagesForShopKeyGenerator() {
        return new CategoryPagesForShopKeyGenerator();
    }

}
