package org.sluja.scraper.shopscraper.product.service.get.implementation;

import org.sluja.scraper.shopscraper.product.dtos.ProductDto;
import org.sluja.scraper.shopscraper.dtos.request.GetProductsForShopRequest;
import org.sluja.scraper.shopscraper.product.service.get.IGetProductsForShop;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsForShopService implements IGetProductsForShop<List<ProductDto>, GetProductsForShopRequest> {
    @Override
    public GetProductsForShopRequest get(List<ProductDto> request) {
        return null;
    }
}
