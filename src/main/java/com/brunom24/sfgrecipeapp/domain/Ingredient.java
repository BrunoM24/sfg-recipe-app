package com.brunom24.sfgrecipeapp.domain;

import javax.persistence.*;
import java.math.BigDecimal;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(UnitOfMeasure unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

}
