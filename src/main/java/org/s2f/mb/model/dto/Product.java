package org.s2f.mb.model.dto;

public class Product {
    private String name;
    private int weight;
    private double price;
    private double priceFor1g;
    private int kcal100g;
    private Boolean isCooked;

    public Product(String name, int weight, double price, int kcal100g, Boolean isCooked) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.priceFor1g = price / weight;
        this.kcal100g = kcal100g;
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

    public double getPriceFor1g() {
        return priceFor1g;
    }

    public int getKcal100g() {
        return kcal100g;
    }

    public Boolean getIsCooked() {
        return isCooked;
    }

    public void setPriceFor1g() {
        this.priceFor1g = this.price / this.weight;
    }

    public void setCooked(Boolean cooked) {
        isCooked = cooked;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", priceFor1g=" + priceFor1g +
                ", kcal100g=" + kcal100g +
                ", isCooked=" + isCooked +
                '}';
    }
}
