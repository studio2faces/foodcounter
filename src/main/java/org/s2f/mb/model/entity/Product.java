package org.s2f.mb.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "food")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private int weight;

    @Column(name = "price")
    private double price;

    @Column(name = "kcal")
    private int kcal; //by 100g

    @Column(name = "isCooked")
    private boolean isCooked;

    @Column(name = "uuid")
    private String uuid;

    public Product() {
    }

    public Product(String name, int weight, double price, int kcal, boolean isCooked, String uuid) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.kcal = kcal;
        this.isCooked = isCooked;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public int getKcal() {
        return kcal;
    }

    public Boolean getIsCooked() {
        return isCooked;
    }

    public double priceByOneGramm() {
        return this.price / this.weight;
    }

    public void setCooked(boolean cooked) {
        isCooked = cooked;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", kcal=" + kcal +
                ", isCooked=" + isCooked +
                ", uuid=" + uuid +
                '}';
    }
}