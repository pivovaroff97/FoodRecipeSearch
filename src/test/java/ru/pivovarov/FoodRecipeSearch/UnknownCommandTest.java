package ru.pivovarov.FoodRecipeSearch;

import ru.pivovarov.FoodRecipeSearch.command.Command;
import ru.pivovarov.FoodRecipeSearch.command.UnknownCommand;

public class UnknownCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return "/someTestCommand";
    }

    @Override
    String getCommandMessage() {
        return UnknownCommand.UNKNOWN_COMMAND_TEXT;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendMessageService);
    }
}
