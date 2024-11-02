package org.sluja.scraper.shopscraper.scraper.implementation.categoryPage.dtos.cache;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.sluja.scraper.shopscraper.scraper.exceptions.cache.IncorrectCacheObjectException;

import java.io.Serializable;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CategoryPagesForShopCacheObject implements Serializable {

    private final String shopName;
    private final String context;
    @EqualsAndHashCode.Exclude
    private final List<String> categoryPages;

    public static CategoryPagesForShopCacheObject of(final String shopName,
                                                final String context,
                                                final List<String> categoryPages) {
        return new CategoryPagesForShopCacheObject(shopName, context, categoryPages);
    }

    private CategoryPagesForShopCacheObject(final String shopName,
                                       final String context,
                                       final List<String> categoryPages) {
        if(isNotValid(shopName, context, categoryPages)) {
            throw new IncorrectCacheObjectException();
        }
        this.shopName = shopName;
        this.context = context;
        this.categoryPages = categoryPages;
    }

    private static boolean validate(final String shopName,
                                    final String context,
                                    final List<String> categoryPages) {
        return CollectionUtils.isNotEmpty(categoryPages)
                && categoryPages.stream().allMatch(StringUtils::isNotBlank)
                && StringUtils.isNotBlank(shopName)
                && StringUtils.isNotBlank(context);
    }

    private static boolean isValid(final String shopName,
                                   final String context,
                                   final List<String> categoryPages) {
        return validate(shopName, context, categoryPages);
    }

    private static boolean isNotValid(final String shopName,
                                      final String context,
                                      final List<String> categoryPages) {
        return !isValid(shopName, context, categoryPages);
    }


}
