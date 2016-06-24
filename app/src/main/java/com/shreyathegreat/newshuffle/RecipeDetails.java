package com.shreyathegreat.newshuffle;

/**
 * Created by shreya on 11/3/16.
 */
public class RecipeDetails {
    int id;
    String prep_time, cook_time, ingredients, steps, tip;
    FoodItem foodItem;

    public RecipeDetails( int id, String prep_time, String cook_time, String ingredients, String steps, String tip, FoodItem foodItem) {
        this.cook_time = cook_time;
        this.id = id;
        this.ingredients = ingredients;
        this.prep_time = prep_time;
        this.steps = steps;
        this.tip = tip;
        this.foodItem = foodItem;
    }
}
