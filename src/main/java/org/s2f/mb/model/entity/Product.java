package org.s2f.mb.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "food")
@Data
@NoArgsConstructor
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

    public double countPriceByOneGramm() {
        return this.price / this.weight;
    }
}