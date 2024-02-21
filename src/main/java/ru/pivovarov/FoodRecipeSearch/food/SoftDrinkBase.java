package ru.pivovarov.FoodRecipeSearch.food;

public enum SoftDrinkBase implements Food {
    SWEETENED_BEVERAGES("sweetened beverages"),
    JUICE("juice");

    private final String name;
    SoftDrinkBase(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
