package io.github.pityx.bot;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class PityxBot extends TelegramLongPollingBot {
    private static final String ANSWER = "Ты питух, ";

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = null;
        StringBuilder answerText = new StringBuilder(ANSWER);

        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId().toString();
            answerText.append(update.getMessage().getFrom().getFirstName());
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.enableNotification();
        sendMessage.setChatId(chatId);
        sendMessage.setText(answerText.toString());
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
