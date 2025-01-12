package com.Recipes;

import java.awt.image.BufferedImage;

public class Recipe {
    private String title;
    private String ingredients;
    private BufferedImage ingredientImage;

    public Recipe(String title, String ingredients, BufferedImage ingredientImage) {
        this.title = title;
        this.ingredients = ingredients;
        this.ingredientImage = ingredientImage;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public BufferedImage getIngredientImage() {
        return ingredientImage;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nIngredients: " + ingredients + "\n";
    }
}
