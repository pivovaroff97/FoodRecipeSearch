package ru.pivovarov.FoodRecipeSearch.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pivovarov.FoodRecipeSearch.service.SendMessageService;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private final Map<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendMessageService sendMessageService) {
        commandMap = new HashMap<>();
        commandMap.put(CommandName.START.getCommandName(), new StartCommand(sendMessageService));
        unknownCommand = new UnknownCommand(sendMessageService);
    }

    public Command getCommand(Update update) {
        String message = update.getMessage().getText();
        return commandMap.getOrDefault(message, unknownCommand);
    }
}
