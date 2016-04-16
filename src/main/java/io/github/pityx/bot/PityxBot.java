package io.github.pityx.bot;

import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.codemonkey.simplejavamail.email.Email;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.Arrays;

public class PityxBot extends TelegramLongPollingBot {
    private static final String ANSWER = "Ты питух, ";
    private static final Mailer mailer = new Mailer(Mail.CONFIG.server(), 465, Mail.CONFIG.username(), Mail.CONFIG.password(), TransportStrategy.SMTP_SSL);

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = null;
        StringBuilder answerText = new StringBuilder(ANSWER);

        if (update.hasInlineQuery()) {
            chatId = update.getInlineQuery().getFrom().getId().toString();
            answerText.append(update.getInlineQuery().getFrom().getFirstName());
        } else if (update.hasChosenInlineQuery()) {
            chatId = update.getChosenInlineQuery().getFrom().getId().toString();
            answerText.append(update.getChosenInlineQuery().getFrom().getFirstName());
        } else if (update.hasMessage()) {
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
            Email email = new Email.Builder()
                    .from("PityxBot", Mail.CONFIG.username())
                    .to("DEV", Mail.CONFIG.devAddress())
                    .subject("Exception")
                    .text(Arrays.toString(e.getStackTrace()))
                    .build();
            mailer.sendMail(email);
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
