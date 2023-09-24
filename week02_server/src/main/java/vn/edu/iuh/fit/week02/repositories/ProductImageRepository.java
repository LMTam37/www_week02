package vn.edu.iuh.fit.week02.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.ProductImage;
import vn.edu.iuh.fit.week02.utils.JPAUtil;

import java.util.Optional;

public class ProductImageRepository {

    private final EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final EntityTransaction trans;

    public ProductImageRepository() {
        this.entityManager = JPAUtil.getEntityManager();
        this.trans = entityManager.getTransaction();
    }

    public Optional<ProductImage> findById(Long productId, Long imageId) {
        String sql = "SELECT pi FROM ProductImage pi WHERE pi.product.productId = :productId AND pi.imageId = :imageId";
        TypedQuery<ProductImage> query = entityManager.createQuery(sql, ProductImage.class);
        query.setParameter("productId", productId);
        query.setParameter("imageId", imageId);
        ProductImage productImage = query.getSingleResult();
        return Optional.ofNullable(productImage);
    }

    public void save(ProductImage productImage) {
        try {
            trans.begin();
            entityManager.persist(productImage);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void update(ProductImage productImage) {
        try {
            trans.begin();
            entityManager.merge(productImage);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void delete(Long productId, Long imageId) {
        try {
            trans.begin();
            Optional<ProductImage> productImage = findById(productId, imageId);
            productImage.ifPresent(entityManager::remove);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }

    }
}

