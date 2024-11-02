package org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.config.cache;

import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.dtos.cache.CategoryPagesForShopCacheObject;
import org.sluja.scraper.shopscraper.scraper.general.exceptions.cache.IncorrectCacheObjectException;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CategoryPagesForShopKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        if(params.length > 0 && params[0] instanceof CategoryPagesForShopCacheObject o) {
            return buildKey(o.getShopName(), o.getContext());
        }
        return StringUtils.EMPTY;
    }

    private String buildKey(final String shopName, final String context) {
        if(StringUtils.isEmpty(shopName) || StringUtils.isEmpty(context)) {
            throw new IncorrectCacheObjectException();
        }
        try {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            final String currentDate = LocalDate.now().format(formatter);
            return STR."\{shopName}_\{context}_\{currentDate}";
        } catch(DateTimeException ex) {
            throw new IncorrectCacheObjectException();
        }
    }

}
