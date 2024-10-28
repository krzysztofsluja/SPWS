package org.sluja.scraper.shopscraper.scraper.categoryPage.dtos.cache;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.scraper.exceptions.cache.IncorrectCacheObjectException;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CategoryPagesForShopObject implements Serializable {

    private final String shopName;
    private final String context;
    @EqualsAndHashCode.Exclude
    private final List<String> categoryPages;

    public static CategoryPagesForShopObject of(final String shopName,
                                                final String context,
                                                final List<String> categoryPages) {
        return new CategoryPagesForShopObject(shopName, context, categoryPages);
    }

    private CategoryPagesForShopObject(final String shopName,
                                       final String context,
                                       final List<String> categoryPages) {
        if(CollectionUtils.isEmpty(categoryPages)) {
            throw new IncorrectCacheObjectException();
        }
        this.shopName = shopName;
        this.context = context;
        this.categoryPages = categoryPages;
    }



}
