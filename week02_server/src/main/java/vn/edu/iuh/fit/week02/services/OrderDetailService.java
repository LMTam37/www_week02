package vn.edu.iuh.fit.week02.services;

import vn.edu.iuh.fit.week02.models.OrderDetail;

import java.util.Optional;

public interface OrderDetailService {

    Optional<OrderDetail> findById(Long orderId, Long productId);

    void save(OrderDetail orderDetail);

    void update(OrderDetail orderDetail);

    void delete(Long orderId, Long productId);
}
