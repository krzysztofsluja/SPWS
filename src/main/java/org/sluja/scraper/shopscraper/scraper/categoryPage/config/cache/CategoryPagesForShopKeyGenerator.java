package org.sluja.scraper.shopscraper.scraper.categoryPage.config.cache;

import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.scraper.categoryPage.dtos.cache.CategoryPagesForShopObject;
import org.sluja.scraper.shopscraper.scraper.exceptions.cache.IncorrectCacheObjectException;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CategoryPagesForShopKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        if(params.length > 0 && params[0] instanceof CategoryPagesForShopObject o) {
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
