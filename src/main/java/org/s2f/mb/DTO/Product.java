package org.s2f.mb.DTO;

public class Product {
    private String productName;
    private int weightBy1g;
    private double price;
    private int kcal100g;
    private String isCooked; //'y','n'

    public Product(String productName, int weightBy1g, double price, int kcal100g, String isCooked) {
        this.productName = productName;
        this.weightBy1g = weightBy1g;
        this.price = price;
        this.kcal100g = kcal100g;
        this.isCooked = isCooked;
    }

    public String getProductName() {
        return productName;
    }

    public int getWeightBy1g() {
        return weightBy1g;
    }

    public double getPrice() {
        return price;
    }

    public int getKcal100g() {
        return kcal100g;
    }

    public String getIsCooked() {
        return isCooked;
    }

    public void setIsCooked(String isCooked) {
        this.isCooked = isCooked;
    }
}
