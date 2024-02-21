package ru.pivovarov.FoodRecipeSearch.food;

import lombok.Data;
import ru.pivovarov.FoodRecipeSearch.command.button.NameHelper;

import java.util.*;

@Data
public class DrinkHelper {
    private Set<String> allCocktailCategories = new HashSet<>(List.of(
            "beer",
            "wines",
            "liquor and cocktails",
            "sweetened beverages",
            "juice",
            "fruits",
            "frozen treats",
            "condiments and sauces",
            "plant based protein",
            "cured meats",
            "sugars",
            "vegetables",
            "dairy",
            "water",
            "yogurt",
            "canned vegetables",
            "ready-to-eat cereals",
            "Vegan products",
            "chocolate",
            "quick breads and pastries",
            "eggs",
            "sugar syrups",
            "coffee and tea",
            "flavored water"
    ));

    private Map<String, String> stepToAction = new HashMap<>() {{
        put(NameHelper.ALCOHOL_COCKTAIL_STEP, "next");
        put(NameHelper.ALCOHOL_FREE_STEP, "next");
        put(NameHelper.ALCOHOL_STEP, "next");
        put(NameHelper.SOFT_DRINK_STEP, "next");
        put(NameHelper.SUPPLEMENT_STEP, "next");
        put(NameHelper.RECIPE_STEP, "find");
        put("next", "next");
    }};
}
