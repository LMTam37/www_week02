package vn.edu.iuh.fit.week02.services;

import vn.edu.iuh.fit.week02.models.ProductImage;

import java.util.Optional;

public interface ProductImageService {
    Optional<ProductImage> findById(Long productId, Long imageId);

    void save(ProductImage productImage);

    void update(ProductImage productImage);

    void delete(Long productId, Long imageId);
}
