package vn.edu.iuh.fit.week02.services.impl;

import vn.edu.iuh.fit.week02.models.Order;
import vn.edu.iuh.fit.week02.repositories.OrderRepository;
import vn.edu.iuh.fit.week02.services.OrderService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl() {
        this.orderRepository = new OrderRepository();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        orderRepository.update(order);
    }

    @Override
    public void delete(Long orderId) {
        orderRepository.delete(orderId);
    }

    @Override
    public List<Order> findByOrderDate(Date date) {
        return orderRepository.findByOrderDate(date);
    }

    @Override
    public List<Order> findByOrderDateRange(Date startDate, Date endDate) {
        return orderRepository.findByOrderDateRange(startDate, endDate);
    }
}
