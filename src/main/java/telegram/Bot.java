package telegram;

import com.annimon.tgbotsmodule.BotHandler;
import com.annimon.tgbotsmodule.BotModule;
import com.annimon.tgbotsmodule.Runner;
import com.annimon.tgbotsmodule.beans.Config;
import org.telegram.telegrambots.ApiContextInitializer;

import java.util.List;

public class Bot implements BotModule {

    public static void main(String[] args) {
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("socksProxyHost", "80.211.24.203");

        System.getProperties().put("socksProxyPort", "1080");
        ApiContextInitializer.init();
        final var profile = (args.length >= 1 && !args[0].isEmpty()) ? args[0] : "";
        Runner.run(profile, List.of(new Bot()));
    }

    @Override
    public BotHandler botHandler(Config config) {
        return new BotHandlerImpl();
    }
}
