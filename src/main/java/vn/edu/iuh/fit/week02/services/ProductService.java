package vn.edu.iuh.fit.week02.services;

import vn.edu.iuh.fit.week02.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Long productId);

    void save(Product product);

    void update(Product product);

    void delete(Long productId);
}
