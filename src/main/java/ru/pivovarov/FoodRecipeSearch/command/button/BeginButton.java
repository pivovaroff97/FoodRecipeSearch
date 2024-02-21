package ru.pivovarov.FoodRecipeSearch.command.button;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pivovarov.FoodRecipeSearch.command.Command;
import ru.pivovarov.FoodRecipeSearch.command.KeyBoardGenerator;
import ru.pivovarov.FoodRecipeSearch.food.AlcoholBase;
import ru.pivovarov.FoodRecipeSearch.service.SendMessageService;

public class BeginButton implements Command, Button {

    private final SendMessageService sendMessageService;

    private final KeyBoardGenerator keyBoardGenerator;

    public BeginButton(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
        this.keyBoardGenerator = new KeyBoardGenerator();
    }

    @Override
    public void execute(Update update) {
        sendMessageService.sendMessage(
                update.getCallbackQuery().getMessage().getChatId(),
                NameHelper.CHOOSE_ALCOHOL_FREE_OR_NOT_FREE,
                keyBoardGenerator.getFoodKeyBoard(AlcoholBase.values())
        );
    }
}
