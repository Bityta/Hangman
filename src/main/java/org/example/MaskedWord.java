package org.example;

import java.util.ArrayList;

public class MaskedWord {

    ArrayList<String> maskedWord;
    String zat = "*"; //изменить название

    public MaskedWord(String secretWord) {

        maskedWord = new ArrayList<>();
        for (int i = 0; i < secretWord.length(); i++) {
            maskedWord.add(zat);
        }
    }

    public ArrayList<String> getMaskedWord() {
        return maskedWord;
    }

    public void updateMaskedWord(String letter, String secretWord) {
        for (int i = 0; i < secretWord.length(); i++) {
            if (letter.charAt(0) == secretWord.charAt(i)) {
                maskedWord.set(i, letter);
            }
        }

    }

    public boolean isHasLetter(String letter, String secretWord) {//изменить название
        for (int i = 0; i < secretWord.length(); i++) {
            if (letter.charAt(0) == secretWord.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOpen(){ //изменить название
        for (String i: maskedWord) {
            if (i.equals(zat)) return false;

        }
        return true;
    }
}
