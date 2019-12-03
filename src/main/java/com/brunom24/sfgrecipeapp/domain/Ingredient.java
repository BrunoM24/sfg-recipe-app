package com.brunom24.sfgrecipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne
    private UnitOfMeasure unitMeasure;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitMeasure) {
        this.description = description;
        this.amount = amount;
        this.unitMeasure = unitMeasure;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitMeasure, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.unitMeasure = unitMeasure;
        this.recipe = recipe;
    }

}
