package ru.pivovarov.FoodRecipeSearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pivovarov.FoodRecipeSearch.FoodRecipeSearchBot;

@Service
public class SendMessageServiceImpl implements SendMessageService {

    private final FoodRecipeSearchBot foodRecipeSearchBot;

    @Autowired
    public SendMessageServiceImpl(FoodRecipeSearchBot foodRecipeSearchBot) {
        this.foodRecipeSearchBot = foodRecipeSearchBot;
    }

    @Override
    public void sendMessage(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            foodRecipeSearchBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
