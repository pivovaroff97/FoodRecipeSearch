package ru.pivovarov.FoodRecipeSearch.food;

public enum AlcoholBase implements Food {

    ALCOHOL_FREE("alcohol-free"),
    ALCOHOL_BASE("alcohol-cocktail");

    private final String name;
    AlcoholBase(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
