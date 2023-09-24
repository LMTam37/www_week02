package vn.edu.iuh.fit.week02.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.Order;
import vn.edu.iuh.fit.week02.utils.JPAUtil;

import java.sql.Date;
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

    public List<Order> findByOrderDate(Date date) {
        String jpql = "SELECT o FROM Order o WHERE FUNCTION('DATE', o.orderDate) = :date";
        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    public List<Order> findByOrderDateRange(Date startDate, Date endDate) {
        String jpql = "SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate";
        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
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

