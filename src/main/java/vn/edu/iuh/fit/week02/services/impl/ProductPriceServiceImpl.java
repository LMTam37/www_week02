package vn.edu.iuh.fit.week02.services.impl;

import vn.edu.iuh.fit.week02.models.ProductPrice;
import vn.edu.iuh.fit.week02.repositories.ProductPriceRepository;
import vn.edu.iuh.fit.week02.services.ProductPriceService;

import java.sql.Date;
import java.util.Optional;

public class ProductPriceServiceImpl implements ProductPriceService {
    private final ProductPriceRepository productPriceRepository;

    public ProductPriceServiceImpl() {
        this.productPriceRepository = new ProductPriceRepository();
    }

    @Override
    public Optional<ProductPrice> findById(Long productId, Date priceDateTime) {
        return productPriceRepository.findById(productId, priceDateTime);
    }

    @Override
    public void save(ProductPrice productPrice) {
        productPriceRepository.save(productPrice);
    }

    @Override
    public void update(ProductPrice productPrice) {
        productPriceRepository.update(productPrice);
    }

    @Override
    public void delete(Long productId, Date priceDateTime) {
        productPriceRepository.delete(productId, priceDateTime);
    }
}
