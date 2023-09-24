package vn.edu.iuh.fit.week02.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.models.OrderDetail;
import vn.edu.iuh.fit.week02.utils.JPAUtil;

import java.util.Optional;

public class OrderDetailRepository {

    private final EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final EntityTransaction trans;

    public OrderDetailRepository() {
        this.entityManager = JPAUtil.getEntityManager();
        this.trans = entityManager.getTransaction();
    }

    public Optional<OrderDetail> findById(Long orderId, Long productId) {
        String sql = "SELECT od FROM OrderDetail od WHERE od.order.orderId = :orderId AND od.product.productId = :productId";
        TypedQuery<OrderDetail> query = entityManager.createQuery(sql, OrderDetail.class);
        query.setParameter("orderId", orderId);
        query.setParameter("productId", productId);
        OrderDetail orderDetail = query.getSingleResult();
        return Optional.ofNullable(orderDetail);
    }


    public void save(OrderDetail orderDetail) {
        try {
            trans.begin();
            entityManager.persist(orderDetail);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void update(OrderDetail orderDetail) {
        try {
            trans.begin();
            entityManager.merge(orderDetail);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void delete(Long orderId, Long productId) {
        try {
            trans.begin();
            Optional<OrderDetail> orderDetail = findById(orderId, productId);
            orderDetail.ifPresent(entityManager::remove);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }
}

