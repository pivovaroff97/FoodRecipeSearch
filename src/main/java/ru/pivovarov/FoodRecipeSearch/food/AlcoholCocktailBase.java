package ru.pivovarov.FoodRecipeSearch.food;

public enum AlcoholCocktailBase implements Food {
    BEER("beer"),
    WINE("wines"),
    STRONG_ALCOHOL("liquor and cocktails");

    private final String name;
    AlcoholCocktailBase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
