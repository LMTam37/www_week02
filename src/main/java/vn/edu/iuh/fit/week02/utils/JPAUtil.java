package vn.edu.iuh.fit.week02.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("lab_week_02");
    public static EntityManager getEntityManager(){
        return emFactory.createEntityManager();
    }

    public static void closeEntityManagerFactory(){
        emFactory.close();
    }
}
