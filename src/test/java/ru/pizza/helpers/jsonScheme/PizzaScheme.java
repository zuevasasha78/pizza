package ru.pizza.helpers.jsonScheme;

import java.util.ArrayList;

public class PizzaScheme {
    private ArrayList ingredients;

    public ArrayList getIngredients() {
        return ingredients;
    }

    public PizzaScheme setIngredients(ArrayList ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
