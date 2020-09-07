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

    @Column(name = "priceByOneGramm")
    private double priceByOneGramm;

    @Column(name = "kcal")
    private int kcal; //by 100g

    @Column(name = "isCooked")
    private boolean isCooked;

    @Column(name = "uuid")
    private String uuid;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceByOneGramm() {
        return priceByOneGramm;
    }

    public void setPriceByOneGramm(double priceByOneGramm) {
        this.priceByOneGramm = priceByOneGramm;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public boolean isCooked() {
        return isCooked;
    }

    public void setCooked(boolean cooked) {
        isCooked = cooked;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public double countPriceByOneGramm() {
        return this.price / this.weight;
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