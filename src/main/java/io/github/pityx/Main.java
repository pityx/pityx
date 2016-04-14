package io.github.pityx;

import io.github.pityx.bot.PityxBot;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

public class Main {
    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new PityxBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
