package vn.edu.iuh.fit.week02.services.impl;

import vn.edu.iuh.fit.week02.models.ProductImage;
import vn.edu.iuh.fit.week02.repositories.ProductImageRepository;
import vn.edu.iuh.fit.week02.services.ProductImageService;

import java.util.Optional;

public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;

    public ProductImageServiceImpl() {
        this.productImageRepository = new ProductImageRepository();
    }

    @Override
    public Optional<ProductImage> findById(Long productId, Long imageId) {
        return productImageRepository.findById(productId, imageId);
    }

    @Override
    public void save(ProductImage productImage) {
        productImageRepository.save(productImage);
    }

    @Override
    public void update(ProductImage productImage) {
        productImageRepository.update(productImage);
    }

    @Override
    public void delete(Long productId, Long imageId) {
        productImageRepository.delete(productId, imageId);
    }
}
