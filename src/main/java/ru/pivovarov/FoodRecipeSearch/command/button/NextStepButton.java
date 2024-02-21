package ru.pivovarov.FoodRecipeSearch.command.button;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pivovarov.FoodRecipeSearch.command.Command;
import ru.pivovarov.FoodRecipeSearch.command.KeyBoardGenerator;
import ru.pivovarov.FoodRecipeSearch.food.*;
import ru.pivovarov.FoodRecipeSearch.service.SendMessageService;

public class NextStepButton implements Command, Button {

    private final SendMessageService sendMessageService;

    private final KeyBoardGenerator keyBoardGenerator;

    private final CocktailParamsState cocktailParamsState;

    public NextStepButton(SendMessageService sendMessageService, CocktailParamsState cocktailParamsState) {
        this.sendMessageService = sendMessageService;
        this.keyBoardGenerator = new KeyBoardGenerator();
        this.cocktailParamsState = cocktailParamsState;
    }

    @Override
    public void execute(Update update) {
        Food[] foods;
        String text;
        switch (cocktailParamsState.getStepName()) {
            case NameHelper.ALCOHOL_FREE_STEP -> {
                foods = SupplementAlcoholFreeBase.values();
                text = NameHelper.CHOOSE_SUPPLEMENT_DRINK;
                cocktailParamsState.setStepName(NameHelper.RECIPE_STEP);
            }
            case NameHelper.ALCOHOL_COCKTAIL_STEP -> {
                foods = AlcoholCocktailBase.values();
                text = NameHelper.CHOOSE_ALCOHOL_DRINK;
                cocktailParamsState.setStepName(NameHelper.SOFT_DRINK_STEP);
            }
            case NameHelper.SOFT_DRINK_STEP -> {
                foods = SoftDrinkBase.values();
                text = NameHelper.CHOOSE_SOFT_DRINK;
                cocktailParamsState.setStepName(NameHelper.SUPPLEMENT_STEP);
            }
            case NameHelper.SUPPLEMENT_STEP -> {
                foods = SupplementBase.values();
                text = NameHelper.CHOOSE_SUPPLEMENT_DRINK;
                cocktailParamsState.setStepName(NameHelper.RECIPE_STEP);
            }
            default -> {
                foods = new Food[]{};
                text = NameHelper.UNKNOWN_ERROR;
            }
        }
        sendMessageService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                update.getCallbackQuery().getMessage().getMessageId(),
                text,
                keyBoardGenerator.getFoodKeyBoard(foods));
    }
}
