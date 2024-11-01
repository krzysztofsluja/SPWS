package org.sluja.scraper.shopscraper.scraper.categoryPage.config.cache;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CategoryPagesForShopCacheConfiguration {

    @Bean
    public Cache<Object, Object> categoryPagesForShopCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .build();
    }
}
