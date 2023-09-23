package vn.edu.iuh.fit.week02.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.Order;
import vn.edu.iuh.fit.week02.utils.JPAUtil;

import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private final EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final EntityTransaction trans;

    public OrderRepository() {
        this.entityManager = JPAUtil.getEntityManager();
        this.trans = entityManager.getTransaction();
    }

    public Optional<Order> findById(Long orderId) {
        return Optional.ofNullable(entityManager.find(Order.class, orderId));
    }

    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    public void save(Order order) {
        try {
            trans.begin();
            entityManager.persist(order);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void update(Order order) {
        try {
            trans.begin();
            entityManager.merge(order);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void delete(Long orderId) {
        try {
            trans.begin();
            Optional<Order> order = findById(orderId);
            order.ifPresent(entityManager::remove);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }
}

