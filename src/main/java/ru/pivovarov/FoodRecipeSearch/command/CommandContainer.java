package ru.pivovarov.FoodRecipeSearch.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pivovarov.FoodRecipeSearch.client.RecipeClient;
import ru.pivovarov.FoodRecipeSearch.command.button.FindButton;
import ru.pivovarov.FoodRecipeSearch.command.button.NameHelper;
import ru.pivovarov.FoodRecipeSearch.command.button.NextStepButton;
import ru.pivovarov.FoodRecipeSearch.command.button.CocktailParamsState;
import ru.pivovarov.FoodRecipeSearch.food.DrinkHelper;
import ru.pivovarov.FoodRecipeSearch.service.SendMessageService;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private final Map<String, Command> commandMap;
    private final Command unknownCommand;
    private final CocktailParamsState cocktailParamsState;
    private final DrinkHelper drinkHelper;

    public CommandContainer(SendMessageService sendMessageService, RecipeClient recipeClient) {
        commandMap = new HashMap<>();
        commandMap.put(CommandName.START.getCommandName(), new StartCommand(sendMessageService));
        cocktailParamsState = new CocktailParamsState();
        cocktailParamsState.setStepName(NameHelper.ALCOHOL_OR_NOT_STEP);
        drinkHelper = new DrinkHelper();
        commandMap.put("next", new NextStepButton(sendMessageService, cocktailParamsState));
        commandMap.put("find", new FindButton(sendMessageService, recipeClient, cocktailParamsState));
        unknownCommand = new UnknownCommand(sendMessageService);
    }

    public Command getCommand(Update update) {
        String message = update.getMessage().getText();
        return commandMap.getOrDefault(message, unknownCommand);
    }

    public Command getButton(Update update) {
        String callbackMessage = update.getCallbackQuery().getData();
        if (cocktailParamsState.getStepName().equals(NameHelper.ALCOHOL_OR_NOT_STEP)) {
            cocktailParamsState.setHealth(callbackMessage);
            cocktailParamsState.setStepName(callbackMessage);
        }
        String command = drinkHelper.getStepToAction().get(cocktailParamsState.getStepName());
        if (drinkHelper.getAllCocktailCategories().contains(callbackMessage)) {
            cocktailParamsState.getChosenCategory().add(callbackMessage);
        }
        return commandMap.getOrDefault(command, unknownCommand);//TODO unknownCommand does not take callback
    }
}
