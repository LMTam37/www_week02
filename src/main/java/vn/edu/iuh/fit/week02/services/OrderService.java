package vn.edu.iuh.fit.week02.services;

import vn.edu.iuh.fit.week02.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();

    Optional<Order> findById(Long orderId);

    void save(Order order);

    void update(Order order);

    void delete(Long orderId);
}
