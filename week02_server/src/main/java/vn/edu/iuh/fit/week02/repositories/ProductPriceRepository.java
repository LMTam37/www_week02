package vn.edu.iuh.fit.week02.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.ProductPrice;
import vn.edu.iuh.fit.week02.utils.JPAUtil;

import java.sql.Date;
import java.util.Optional;

public class ProductPriceRepository {

    private final EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final EntityTransaction trans;

    public ProductPriceRepository() {
        this.entityManager = JPAUtil.getEntityManager();
        this.trans = entityManager.getTransaction();
    }

    public Optional<ProductPrice> findById(Long productId, Date priceDateTime) {
        String jpql = "SELECT pp FROM ProductPrice pp WHERE pp.product.productId = :productId AND pp.priceDateTime = :priceDateTime";
        TypedQuery<ProductPrice> query = entityManager.createQuery(jpql, ProductPrice.class);
        query.setParameter("productId", productId);
        query.setParameter("priceDateTime", priceDateTime);
        ProductPrice productPrice = query.getSingleResult();
        return Optional.ofNullable(productPrice);
    }


    public void save(ProductPrice productPrice) {
        try {
            trans.begin();
            entityManager.persist(productPrice);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void update(ProductPrice productPrice) {
        try {
            trans.begin();
            entityManager.merge(productPrice);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void delete(Long productId, Date priceDateTime) {
        try {
            trans.begin();
            Optional<ProductPrice> productPrice = findById(productId, priceDateTime);
            productPrice.ifPresent(entityManager::remove);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }
}
