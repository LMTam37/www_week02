package vn.edu.iuh.fit.week02.services.impl;

import vn.edu.iuh.fit.week02.models.OrderDetail;
import vn.edu.iuh.fit.week02.repositories.OrderDetailRepository;
import vn.edu.iuh.fit.week02.services.OrderDetailService;

import java.util.Optional;

public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl() {
        this.orderDetailRepository = new OrderDetailRepository();
    }

    @Override
    public Optional<OrderDetail> findById(Long orderId, Long productId) {
        return orderDetailRepository.findById(orderId, productId);
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void update(OrderDetail orderDetail) {
        orderDetailRepository.update(orderDetail);
    }

    @Override
    public void delete(Long orderId, Long productId) {
        orderDetailRepository.delete(orderId, productId);
    }
}
