package ru.pivovarov.FoodRecipeSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pivovarov.FoodRecipeSearch.client.RecipeClient;
import ru.pivovarov.FoodRecipeSearch.command.CommandContainer;
import ru.pivovarov.FoodRecipeSearch.service.SendMessageServiceImpl;

@Component
public class FoodRecipeSearchBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;

    private final CommandContainer commandContainer;

    @Autowired
    public FoodRecipeSearchBot(@Value("${bot.token}") String botToken, RecipeClient recipeClient) {
        super(botToken);
        commandContainer = new CommandContainer(new SendMessageServiceImpl(this), recipeClient);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            commandContainer.getCommand(update).execute(update);
        } else if (update.hasCallbackQuery()) {
            System.out.println(update.getCallbackQuery().getData());
            commandContainer.getButton(update).execute(update);
        }
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }
}
