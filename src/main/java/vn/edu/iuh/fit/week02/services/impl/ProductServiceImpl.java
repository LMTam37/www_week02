package vn.edu.iuh.fit.week02.services.impl;

import vn.edu.iuh.fit.week02.models.Product;
import vn.edu.iuh.fit.week02.repositories.ProductRepository;
import vn.edu.iuh.fit.week02.services.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl() {
        this.productRepository = new ProductRepository();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public void delete(Long productId) {
        productRepository.delete(productId);
    }
}
