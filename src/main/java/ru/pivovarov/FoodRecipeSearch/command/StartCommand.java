package ru.pivovarov.FoodRecipeSearch.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pivovarov.FoodRecipeSearch.service.SendMessageService;

public class StartCommand implements Command {

    public final static String START_COMMAND_TEXT =
            "Hello. I am recipeBot. I help you to find recipe. Text any food you want";

    private final SendMessageService sendMessageService;

    public StartCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        sendMessageService.sendMessage(update.getMessage().getChatId(), START_COMMAND_TEXT);
    }
}
