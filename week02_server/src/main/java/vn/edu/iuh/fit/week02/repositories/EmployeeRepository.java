package vn.edu.iuh.fit.week02.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.week02.models.Employee;
import vn.edu.iuh.fit.week02.utils.JPAUtil;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository {

    private final EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final EntityTransaction trans;

    public EmployeeRepository() {
        this.entityManager = JPAUtil.getEntityManager();
        this.trans = entityManager.getTransaction();
    }

    public Optional<Employee> findById(Long empId) {
        return Optional.ofNullable(entityManager.find(Employee.class, empId));
    }

    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    public void save(Employee employee) {
        try {
            trans.begin();
            entityManager.persist(employee);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void updateEmployee(Employee employee) {
        try {
            trans.begin();
            entityManager.merge(employee);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    public void deleteEmployee(Long id) {
        try {
            trans.begin();
            Optional<Employee> employee = findById(id);
            employee.ifPresent(entityManager::remove);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }
}
