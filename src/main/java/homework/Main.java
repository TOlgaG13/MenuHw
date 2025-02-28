package homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("menu-hw");
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DishesDAO dishesDAO = new DishesDAO();
        try {
            while (true) {
                System.out.println("Menu options:");
                System.out.println("1. Create new dish");
                System.out.println("2. Show all dishes");
                System.out.println("3. Show dishes by price");
                System.out.println("4. Show dishes only with discount");
                System.out.println("5. Choose dishes up to 1 kg (1000 g)");
                System.out.println("0. Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        saveDishes(sc, dishesDAO);
                        break;
                    case 2:
                        getAllDishes(dishesDAO);
                        break;
                    case 3:
                        getDishesByPriceRange(sc, dishesDAO);
                        break;
                    case 4:
                        getDishesWithDiscount(dishesDAO);
                        break;
                    case 5:
                        getDishesUnderWeightLimit(sc, dishesDAO);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
            em.close();
            emf.close();
        }
    }
    private static void saveDishes(Scanner sc, DishesDAO dishesDAO) {
        System.out.println("Enter dish name:");
        String name = sc.next();
        System.out.println("Enter dish price:");
        double price = sc.nextDouble();
        System.out.println("Enter dish weight:");
        double weight = sc.nextDouble();
        System.out.println("Enter dish discount (true/false):");
        boolean discount = sc.nextBoolean();

        Dishes dish = new Dishes(name, price, weight, discount);
        dishesDAO.saveDishes(dish);
        System.out.println("Dish saved successfully!");
    }

    private static void getAllDishes(DishesDAO dishesDAO) {
        List<Dishes> dishes = dishesDAO.getAllDishes();
        for (Dishes dish : dishes) {
            System.out.println(dish);
        }
    }
    private static void getDishesByPriceRange(Scanner sc, DishesDAO dishesDAO) {
        System.out.println("Enter minimum price:");
        double minPrice = sc.nextDouble();
        System.out.println("Enter maximum price:");
        double maxPrice = sc.nextDouble();

        List<Dishes> dishes = dishesDAO.getDishesByPriceRange(minPrice, maxPrice);
        for (Dishes dish : dishes) {
            System.out.println(dish);
        }
    }
    private static void getDishesWithDiscount(DishesDAO dishesDAO) {
        List<Dishes> dishes = dishesDAO.getDishesWithDiscount();
        for (Dishes dish : dishes) {
            System.out.println(dish);
        }
    }

    private static void getDishesUnderWeightLimit(Scanner sc, DishesDAO dishesDAO) {
        System.out.println("Enter maximum weight (in grams, 1000g = 1kg):");
        double maxWeight = sc.nextDouble();

        List<Dishes> dishes = dishesDAO.getDishesUnderWeightLimit(maxWeight);
        for (Dishes dish : dishes) {
            System.out.println(dish);
        }
    }
}
