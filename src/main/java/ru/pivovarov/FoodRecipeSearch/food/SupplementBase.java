package ru.pivovarov.FoodRecipeSearch.food;

public enum SupplementBase implements Food {
    FRUIT("fruit"),
    FROZEN_TREATS("frozen treats"),
    CONDIMENTS_AND_SAUCES("condiments and sauces"),
    PLANT_BASED_PROTEIN("plant based protein"),
    CURED_MEATS("cured meats"),
    SUGARS("sugars"),
    VEGETABLES("vegetables"),
    DAIRY("dairy"),
    WATER("water");

    private final String name;
    SupplementBase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
