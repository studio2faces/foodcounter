package org.s2f.mb.model.dto;

public class Product {
    private String name;
    private int weight;
    private double price;
    private int kcal; //by 100g
    private boolean isCooked;

    public Product(String name, int weight, double price, int kcal, boolean isCooked) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.kcal = kcal;
        this.isCooked = isCooked;
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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", kcal=" + kcal +
                ", isCooked=" + isCooked +
                '}';
    }
}
