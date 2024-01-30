package ru.pivovarov.FoodRecipeSearch;

import ru.pivovarov.FoodRecipeSearch.command.Command;
import ru.pivovarov.FoodRecipeSearch.command.CommandName;
import ru.pivovarov.FoodRecipeSearch.command.StartCommand;

public class StartCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return CommandName.START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return StartCommand.START_COMMAND_TEXT;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendMessageService);
    }
}
