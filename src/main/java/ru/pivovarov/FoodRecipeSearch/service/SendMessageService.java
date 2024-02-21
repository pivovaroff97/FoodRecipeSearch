package ru.pivovarov.FoodRecipeSearch.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface SendMessageService {
    void sendMessage(Long chatId, String message);
    void sendMessage(Long chatId, String message, InlineKeyboardMarkup kb);
    void sendMessage(Long chatId, Integer messageId, String message, InlineKeyboardMarkup kb);
}
