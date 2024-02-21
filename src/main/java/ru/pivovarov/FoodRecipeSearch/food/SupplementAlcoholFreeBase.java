package ru.pivovarov.FoodRecipeSearch.food;

public enum SupplementAlcoholFreeBase implements Food {

    FRUIT("fruit"),
    FROZEN_TREATS("frozen treats"),
    CONDIMENTS_AND_SAUCES("condiments and sauces"),
    SUGARS("sugars"),
    VEGETABLES("vegetables"),
    DAIRY("dairy"),
    ICE("ice"),
    YOGURT("yogurt"),
    CANNED_VEGETABLES("canned vegetables"),
    READY_TO_EAT_CEREALS("ready-to-eat cereals"),
    VEGAN_PRODUCTS("Vegan products"),
    CHOCOLATE("chocolate"),
    QUICK_BREADS_AND_PASTRIES("quick breads and pastries"),
    EGGS("eggs"),
    SUGAR_SYRUPS("sugar syrups"),
    COFFEE_AND_TEA("coffee and tea"),
    FLAVORED_WATER("flavored water"),
    WATER("water");

    private final String name;
    SupplementAlcoholFreeBase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}