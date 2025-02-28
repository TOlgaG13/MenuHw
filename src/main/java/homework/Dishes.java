package homework;

import jakarta.persistence.*;

@Entity
@Table(name="menu")
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private double price;
    @Column(nullable=false)
    private double weight;
    @Column(nullable=false)
    private boolean discount;

    public Dishes() {
    }

    public Dishes(String name, double price, double weight, boolean discount) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", discount=" + discount +
                '}';
    }
}

