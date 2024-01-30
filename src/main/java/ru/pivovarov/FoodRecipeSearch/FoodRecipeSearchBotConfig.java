package ru.pivovarov.FoodRecipeSearch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class FoodRecipeSearchBotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(FoodRecipeSearchBot foodRecipeSearchBot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(foodRecipeSearchBot);
        return botsApi;
    }
}
