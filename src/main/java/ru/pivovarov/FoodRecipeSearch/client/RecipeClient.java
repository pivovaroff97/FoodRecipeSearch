package ru.pivovarov.FoodRecipeSearch.client;

import org.springframework.http.ResponseEntity;
import ru.pivovarov.FoodRecipeSearch.client.dto.Recipe;
import ru.pivovarov.FoodRecipeSearch.command.button.CocktailParamsState;

public interface RecipeClient {
    ResponseEntity<Recipe[]> getRecipes(CocktailParamsState cocktailParamsState);
}
