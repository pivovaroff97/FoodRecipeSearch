package ru.pivovarov.FoodRecipeSearch.command.button;

import org.springframework.http.ResponseEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pivovarov.FoodRecipeSearch.client.RecipeClient;
import ru.pivovarov.FoodRecipeSearch.client.dto.Recipe;
import ru.pivovarov.FoodRecipeSearch.command.Command;
import ru.pivovarov.FoodRecipeSearch.service.SendMessageService;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class FindButton implements Command, Button {
    private final SendMessageService sendMessageService;
    private final RecipeClient recipeClient;
    private final CocktailParamsState cocktailParamsState;

    public FindButton(SendMessageService sendMessageService, RecipeClient recipeClient, CocktailParamsState cocktailParamsState) {
        this.sendMessageService = sendMessageService;
        this.recipeClient = recipeClient;
        this.cocktailParamsState = cocktailParamsState;
    }

    @Override
    public void execute(Update update) {
        sendMessageService.sendMessage(
                update.getCallbackQuery().getMessage().getChatId(),
                NameHelper.SEARCHING_COCKTAILS);
        ResponseEntity<Recipe[]> recipes = recipeClient.getRecipes(cocktailParamsState);
        cocktailParamsState.clearParams();
        String message;
        if (recipes.getStatusCode().is2xxSuccessful()) {
            message = NameHelper.SOME_COCKTAILS + "\n" + Arrays.stream(Objects.requireNonNull(recipes.getBody()))
                    .map(r -> " recipe: " + r.getLabel())
                    .collect(Collectors.joining("\n"));
        } else if (recipes.getStatusCode().is5xxServerError()) {
            System.out.println(recipes.getStatusCode());
            message = NameHelper.TRY_AGAIN_LATER;
        } else {
            message = recipes.getStatusCode().toString();
        }
        System.out.println("message: " + message);
        sendMessageService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                message);
    }
}
