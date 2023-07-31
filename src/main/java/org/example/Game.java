package org.example;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static org.example.StagesOfHangman.hangman;

public class Game {

    String secretWord;
    ArrayList<Character> guessLetter; //слово(буквы) отгаданные неправельные
    int countUserError;
    MaskedWord maskedWord;

    boolean isGameOver;


    final Scanner scan = new Scanner(System.in);

    public Game() {
        this.secretWord = getRandomWord();
        countUserError = 0;
        this.guessLetter = new ArrayList<>();
        maskedWord = new MaskedWord(secretWord);
        isGameOver = false;

    }

    public void startGame() {

        System.out.println("Да начнется игра!");


        while (!isGameOver) {

            System.out.println(getGallowsStatus()); //висилица
            System.out.println(maskedWord.getMaskedWord());//слово
            System.out.printf("Ошибки (%s): " + guessLetter + "\n", countUserError);//ошибки + буквы используемые

            System.out.print("Буква: ");//ввод буквы


            String letter = scan.next(); //изменить название
            letter = transformationToCorrectLetterFormat(letter); //перевести букву в нижний регист + убрать пробелый

            if (!isCorrectLetter(letter)) { //            проверка валидности буквы
                System.out.println("Введены некоретные символы, повторите попытку!");
                continue;
            }
            if (isHasLetter(letter)) { //в ошибках (добавить еще в отгаданных)
                System.out.println("Вы вводили уже эту букву, повторите попытку!");
                continue;
            }


            if (maskedWord.isHasLetter(letter, secretWord)){
                maskedWord.updateMaskedWord(letter,secretWord);
                if(maskedWord.isOpen()){
                    isGameOver = true;
                    System.out.println("Поздравляю, Вы победили.");
                }
                continue;
            }

            guessLetter.add(letter.charAt(0));
            countUserError++;

            if (countUserError > 6){
                isGameOver = true;
                System.out.println("Увы, Вы проииграли.");
                System.out.println("Загаданное слово : " + secretWord);
            }





        }


    }

    private boolean isCorrectLetter(String letter) {

        if (letter.length() != 1) return false;

        if (!(letter.charAt(0) >= 'А' && letter.charAt(0) <= 'Я')) return false;

        return true;
    }

    private boolean isHasLetter(String letter) {//другое название
        for (char i : guessLetter) {
            if (i == letter.charAt(0)) return true;
        }
        return false;
    }

    private String transformationToCorrectLetterFormat(String letter) {

        return letter.replace(" ", "").toUpperCase(Locale.ROOT);

    }


    private String getGallowsStatus() {

        return hangman[countUserError];
    }


    private String getRandomWord() {

        // получаем слово из resources

        return "СОБАКА";
    }
}
