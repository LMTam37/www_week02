package vn.edu.iuh.fit.week02.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.Product;
import vn.edu.iuh.fit.week02.utils.JPAUtil;

import java.util.List;
import java.util.Optional;

public class ProductRepository {

    private final EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final EntityTransaction trans;

    public ProductRepository() {
        entityManager = JPAUtil.getEntityManager();
        this.trans = entityManager.getTransaction();
    }

    public Optional<Product> findById(Long productId) {
        return Optional.ofNullable(entityManager.find(Product.class, productId));
    }

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public void save(Product product) {
        try {
            trans.begin();
            entityManager.persist(product);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void update(Product product) {
        try {
            trans.begin();
            entityManager.merge(product);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void delete(Long productId) {
        try {
            trans.begin();
            Optional<Product> product = findById(productId);
            product.ifPresent(entityManager::remove);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }
}

