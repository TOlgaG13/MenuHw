package homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {

            entityManagerFactory = Persistence.createEntityManagerFactory("menu-hw" );
        } catch (Exception e) {
            System.err.println("Ошибка при создании EntityManagerFactory." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}

