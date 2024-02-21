package ru.pivovarov.FoodRecipeSearch;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pivovarov.FoodRecipeSearch.client.RecipeClient;
import ru.pivovarov.FoodRecipeSearch.command.Command;
import ru.pivovarov.FoodRecipeSearch.command.CommandName;
import ru.pivovarov.FoodRecipeSearch.command.KeyBoardGenerator;
import ru.pivovarov.FoodRecipeSearch.food.AlcoholBase;
import ru.pivovarov.FoodRecipeSearch.service.SendMessageService;
import ru.pivovarov.FoodRecipeSearch.service.SendMessageServiceImpl;

public abstract class AbstractCommandTest {

    protected FoodRecipeSearchBot foodRecipeSearchBot = Mockito.mock(FoodRecipeSearchBot.class);
    protected RecipeClient recipeClient = Mockito.mock(RecipeClient.class);
    protected SendMessageService sendMessageService = new SendMessageServiceImpl(foodRecipeSearchBot);
    protected KeyBoardGenerator keyBoardGenerator = new KeyBoardGenerator();

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void checkCommand() throws TelegramApiException {
        Long chatId = 123L;
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (getCommandName().equals(CommandName.START.getCommandName())) {
            sendMessage.setParseMode("HTML");
            sendMessage.setReplyMarkup(keyBoardGenerator.getFoodKeyBoard(AlcoholBase.values()));
        }
        sendMessage.setText(getCommandMessage());

        getCommand().execute(update);
        Mockito.verify(foodRecipeSearchBot).execute(sendMessage);
    }
}
