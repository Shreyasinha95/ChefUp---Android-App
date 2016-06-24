package com.shreyathegreat.newshuffle;

/**
 * Created by shreya on 11/3/16.
 */
public class FoodItem {
    int id, image;
    String name, category, region, type;

    public FoodItem(int id, String name, String category, String region, String type, int image) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.region = region;
        this.type = type;
        this.image = image;
    }

}
