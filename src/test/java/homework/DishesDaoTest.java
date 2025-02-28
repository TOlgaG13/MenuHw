package homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class DishesDaoTest {
    private EntityManagerFactory emf;
    private DishesDAO dishesDAO;
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("menu-hw");
        em = emf.createEntityManager();
        dishesDAO = new DishesDAO();

        // Создание объекта Dishes для теста
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Dishes dish1 = new Dishes("Pasta", 12.99, 350.0, false);
        Dishes dish2 = new Dishes("Pizza", 15.49, 500.0, true);
        em.persist(dish1);
        em.persist(dish2);
        transaction.commit();
    }

    @AfterEach
    public void tearDown() {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.createQuery("DELETE FROM Dishes").executeUpdate();
        transaction.commit();
        em.close();
        emf.close();
    }

    @Test
    public void testSaveDishes() {
        Dishes dish = new Dishes("Salad", 8.99, 150.0, true);
        dishesDAO.saveDishes(dish);

        List<Dishes> dishes = dishesDAO.getAllDishes();
        assertEquals(3, dishes.size(), "Should be 3 dishes in the database");
    }

    @Test
    public void testGetAllDishes() {
        List<Dishes> dishes = dishesDAO.getAllDishes();
        assertNotNull(dishes, "Dishes should not be null");
        assertEquals(2, dishes.size(), "Should return 2 dishes initially");
    }

    @Test
    public void testGetDishesByPriceRange() {
        List<Dishes> dishes = dishesDAO.getDishesByPriceRange(10.0, 20.0);
        assertNotNull(dishes);
        assertEquals(2, dishes.size(), "There should be 2 dishes in the price range");
    }

    @Test
    public void testGetDishesWithDiscount() {
        List<Dishes> dishes = dishesDAO.getDishesWithDiscount();
        assertNotNull(dishes);
        assertEquals(1, dishes.size(), "Should return 1 dish with discount");
    }

    @Test
    public void testGetDishesUnderWeightLimit() {
        List<Dishes> dishes = dishesDAO.getDishesUnderWeightLimit(400);
        assertNotNull(dishes);
        assertEquals(1, dishes.size(), "Should return 1 dish under 400g");
    }
}