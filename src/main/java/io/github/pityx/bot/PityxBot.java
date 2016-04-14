package io.github.pityx.bot;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class PityxBot extends TelegramLongPollingBot {
    private static final String ANSWER = "Ты питух, %username%!";
    private static final String USERNAME = "%username%";

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = null;
        String answerText = null;

        if (update.hasInlineQuery()) {
            chatId = update.getInlineQuery().getFrom().getId().toString();
            answerText = ANSWER.replace(USERNAME, update.getInlineQuery().getQuery());
        } else if (update.hasChosenInlineQuery()) {
            chatId = update.getChosenInlineQuery().getFrom().getId().toString();
            answerText = ANSWER.replace(USERNAME, update.getChosenInlineQuery().getQuery());
        } else if (update.hasMessage()) {
            chatId = update.getMessage().getChat().getId().toString();
            answerText = ANSWER.replace(USERNAME, update.getMessage().getText());
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(answerText);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return Bot.CONFIG.getBotName();
    }

    @Override
    public String getBotToken() {
        return Bot.CONFIG.getToken();
    }

}
