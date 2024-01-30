package ru.pivovarov.FoodRecipeSearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pivovarov.FoodRecipeSearch.command.CommandContainer;
import ru.pivovarov.FoodRecipeSearch.service.SendMessageServiceImpl;

@Component
public class FoodRecipeSearchBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;

    private final CommandContainer commandContainer;

    public FoodRecipeSearchBot(@Value("${bot.token}") String botToken) {
        super(botToken);
        commandContainer = new CommandContainer(new SendMessageServiceImpl(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage());
        commandContainer.getCommand(update).execute(update);
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }
}
