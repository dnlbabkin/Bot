package com.example.Bot.controllers;

import org.glassfish.grizzly.http.KeepAlive;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class BotController extends TelegramLongPollingBot {

    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update){
        // Проверка на то, есть ли в обновлении сообщение и текст сообщения
        if(update.hasMessage() && update.getMessage().hasText()){
            //Установка значений
            String message = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            SendMessage sendMessage = new SendMessage().setChatId(chatId).setText(message); // Создание объекта объекта сообщения

            setButtons(sendMessage, message);

            try{
                execute(sendMessage); // Отправка объекта сообщения пользователю
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param s Строка, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendMsg(String chatId, String s){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try{
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     * @return имя бота
     */
    @Override
    public String getBotUsername(){
        return "StoreCreatorBot";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     * @return token для бота
     */
    @Override
    public String getBotToken(){
        return "1250998017:AAEQId0V9db5DgdW2dfla-muZ3IuKGvV4S4";
    }

    public synchronized void setButtons(SendMessage sendMessage, String message) {
        // Создание клавиатуры
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создание списка строк
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавление кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Store Creator Bot"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondtRow = new KeyboardRow();
        // Добавление кнопки во вторую строчку клавиатуры
        keyboardSecondtRow.add(new KeyboardButton("Помощь \uD83C\uDD98"));

        // Добавление всех строчек клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondtRow);

        // Установка этого списка клаавиатуры
        replyKeyboardMarkup.setKeyboard(keyboard);

        if (message.equals("Store Creator Bot")) {
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardFirstRow.add("Выберете язык \uD83C\uDF10");
            keyboardSecondtRow.add("Назад \uD83D\uDD19");
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondtRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
        } else if (message.equals("Помощь \uD83C\uDD98")) {
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardFirstRow.add("Назад \uD83D\uDD19");
            keyboard.add(keyboardFirstRow);
            replyKeyboardMarkup.setKeyboard(keyboard);
            sendMessage.setText("САМ СЕБЕ ПОМОГИ ПИДОР");
        } else if (message.equals("Выберете язык \uD83C\uDF10")) {
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardSecondtRow.clear();
            keyboardFirstRow.add("Русский \uD83C\uDDF7\uD83C\uDDFA");
            keyboardSecondtRow.add("English \uD83C\uDDEC\uD83C\uDDE7");

            KeyboardRow keyBack = new KeyboardRow();

            keyBack.add("Назад \uD83D\uDD19");

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondtRow);
            keyboard.add(keyBack);
            replyKeyboardMarkup.setKeyboard(keyboard);
        } else if (message.equals("English \uD83C\uDDEC\uD83C\uDDE7")) {
            
        }
    }
}
