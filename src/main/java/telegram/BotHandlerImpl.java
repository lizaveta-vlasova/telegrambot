package telegram;

import com.annimon.tgbotsmodule.BotHandler;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

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

        SendMessage sm = new SendMessage(chatId, text);
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            BotLogger.error("SEND", e.toString());
        }
        return null;
    }


    @Override
    public String getBotUsername() {
        return System.getenv("testLizaveta_bot");
    }

    @Override
    public String getBotToken() {
        return System.getenv("1028166269:AAFM0eiftIE_SAqu2Cx-sbp1QQ0LjtDm0ic");
    }
}
