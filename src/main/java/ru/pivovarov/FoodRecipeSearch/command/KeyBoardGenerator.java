package ru.pivovarov.FoodRecipeSearch.command;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.pivovarov.FoodRecipeSearch.food.Food;

import java.util.ArrayList;
import java.util.List;

public class KeyBoardGenerator {

    public InlineKeyboardMarkup getFoodKeyBoard(Food[] foods) {
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (Food food : foods) {
            row.add(InlineKeyboardButton.builder()
                    .text(food.getName()).callbackData(food.getName())
                    .build());
            if (row.size() == 2) {
                list.add(row);
                row = new ArrayList<>();
            }
        }
        if (foods.length % 2 == 1) {
            list.add(row);
        }
        InlineKeyboardButton next = InlineKeyboardButton.builder()
                .text("whatever").callbackData("next")
                .build();
        return InlineKeyboardMarkup.builder()
                .keyboard(list)
                .keyboardRow(List.of(next))
                .build();
    }
}
