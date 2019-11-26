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
        SendMessage sm;
        if ("/start".equalsIgnoreCase(text)) {
            sm = new SendMessage(chatId, "Привет! Как тебя зовут?");
        } else {
            String name = "Привет, " + text + "! Хорошего тебе дня!";

            sm = new SendMessage().setChatId(chatId).setText(name + Data.ARTICLE);
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
        return "flamingo_articleBot";
    }

    @Override
    public String getBotToken() {
        return "1011233277:AAFDodeTzVrUoh2FXtjuOY9sem0uSvTgoEo";
    }
}
