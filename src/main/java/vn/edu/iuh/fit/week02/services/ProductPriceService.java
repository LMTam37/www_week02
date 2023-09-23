package vn.edu.iuh.fit.week02.services;

import vn.edu.iuh.fit.week02.models.ProductPrice;

import java.sql.Date;
import java.util.Optional;

public interface ProductPriceService {
    Optional<ProductPrice> findById(Long productId, Date priceDateTime);

    void save(ProductPrice productPrice);

    void update(ProductPrice productPrice);

    void delete(Long productId, Date priceDateTime);
}
