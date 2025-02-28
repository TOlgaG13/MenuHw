package homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


import java.util.ArrayList;
import java.util.List;

public class DishesDAO {
    public void saveDishes(Dishes dishes) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(dishes);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
    public List<Dishes> getAllDishes() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.createQuery("FROM Dishes", Dishes.class).getResultList();
        } finally {
            entityManager.close();
        }
    }
    public List<Dishes> getDishesByPriceRange(double minPrice, double maxPrice) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.createQuery("FROM Dishes WHERE price BETWEEN :minPrice AND :maxPrice", Dishes.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }
    public List<Dishes> getDishesWithDiscount() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            return entityManager.createQuery("FROM Dishes WHERE discount = true", Dishes.class).getResultList();
        } finally {
            entityManager.close();
        }
    }
    public List<Dishes> getDishesUnderWeightLimit(double maxWeight) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        try {
            List<Dishes> allDishes = entityManager.createQuery("FROM Dishes ORDER BY weight ASC", Dishes.class).getResultList();
            List<Dishes> selectedDishes = new ArrayList<>();
            double totalWeight = 0;

            for (Dishes dish : allDishes) {
                if (totalWeight + dish.getWeight() <= maxWeight) {
                    selectedDishes.add(dish);
                    totalWeight += dish.getWeight();
                } else {
                    break;
                }
            }
            return selectedDishes;
        } finally {
            entityManager.close();
        }
    }
}



