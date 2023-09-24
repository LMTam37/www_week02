package vn.edu.iuh.fit.week02.services;

import vn.edu.iuh.fit.week02.models.Order;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();

    Optional<Order> findById(Long orderId);

    void save(Order order);

    void update(Order order);

    void delete(Long orderId);

    List<Order> findByOrderDate(Date date);

    List<Order> findByOrderDateRange(Date startDate, Date endDate);

    List<Order> findBySalespersonAndDateRange(Long empId, Date startDate, Date endDate);
}
