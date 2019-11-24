package telegram;

import com.annimon.tgbotsmodule.BotHandler;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

import java.util.ArrayList;
import java.util.List;

public class BotHandlerImpl extends BotHandler {
    @Override
    public BotApiMethod onUpdate(Update update) {
        if (!update.hasMessage()) {
            return null;
        }

        Message message = update.getMessage();
        if (!message.hasText()) {
            return null;
        }

        String text = message.getText();
        long chatId = message.getChatId();
        SendMessage sm;
        if ("/start".equalsIgnoreCase(text)) {
            sm = new SendMessage(chatId, "Здравствуйте! Как можно к Вам обращаться?");
        } else {
            String name = "Добрый день " + text + "! Выберете интересующие Вас опции :";

            InlineKeyboardMarkup inlineKeyboardMarkup =new InlineKeyboardMarkup();
            InlineKeyboardButton firstButton = new InlineKeyboardButton();
            firstButton.setText("визы");
            firstButton.setCallbackData("Информация по визам");

            InlineKeyboardButton secondButton = new InlineKeyboardButton();
            secondButton.setText("путевки");
            secondButton.setCallbackData("Отправить заявку");

            List<InlineKeyboardButton> row = List.of(firstButton, secondButton);
            List<List<InlineKeyboardButton>> rowList= new ArrayList<>();
            rowList.add(row);
            inlineKeyboardMarkup.setKeyboard(rowList);

            sm = new SendMessage().setChatId(chatId).setText(name).setReplyMarkup(inlineKeyboardMarkup);
        }

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            BotLogger.error("SEND", e.toString());
        }
        return null;
    }


    @Override
    public String getBotUsername() {
        return "testLizaveta_bot";
    }

    @Override
    public String getBotToken() {
        return "1028166269:AAFM0eiftIE_SAqu2Cx-sbp1QQ0LjtDm0ic";
    }
}
