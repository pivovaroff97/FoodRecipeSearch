package ru.pivovarov.FoodRecipeSearch.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pivovarov.FoodRecipeSearch.service.SendMessageService;

public class UnknownCommand implements Command {

    public static String UNKNOWN_COMMAND_TEXT = "Неизвестная команда";
    private final SendMessageService sendMessageService;

    public UnknownCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        sendMessageService.sendMessage(update.getMessage().getChatId(), UNKNOWN_COMMAND_TEXT);
    }
}
