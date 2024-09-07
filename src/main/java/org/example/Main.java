package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static String wordDisplay = "";
    private static Integer mistakesCount = 0;

    private static List<String> usedLetters = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Добро пожаловать в игру! Нажмите [Н]ачать или [В]ыйти");
        String str = sc.next();
        startTheGame(str);

        String word = takeNewRandomWordFromFile();
        System.out.println("Кол-во букв в слове - " + word.length());

        wordDisplay = starsLettersInTheWord(word);
        System.out.println(wordDisplay);

        do {
            checkInputLetterInTheWord(inputValidLetter(sc), word);
            System.out.println(wordDisplay);

            if (mistakesCount > 0)
                printHangman(mistakesCount);
            if (!wordDisplay.contains("*")){
                System.out.println("\nВы выиграли! Поздравляю!!!");
                return;
            }

            System.out.println("Использованные буквы: " + usedLetters);
        } while (mistakesCount < 6);

        if (mistakesCount == 6) System.out.println("\nВы проиграли! Попробуйте еще раз.");

        sc.close();
    }

    private static char inputValidLetter(Scanner input) {
        while (true) {
            System.out.println("Введите букву");
            String letter = input.next();

            if (letter.length() != 1)
                System.out.println("Введите одну букву");
            if (usedLetters.contains(letter)) {
                System.out.println("Повтор. Выберите другую букву");
                continue;
            }

            usedLetters.add(letter);

            return letter.charAt(0);
        }
    }

    private static void startTheGame(String s) {

        switch (s.toUpperCase()) {
            case "Н":
                break;
            case "В":
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException("Invalid command was given --- " + s);
        }

    }

    private static void checkInputLetterInTheWord(char letter, String word) {

        char[] letters = wordDisplay.toCharArray();

        if (word.contains(String.valueOf(letter))) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    letters[i] = letter;
                }
            }
        } else {
            mistakesCount++;
        }
        wordDisplay = new String(letters);
    }


    private static String starsLettersInTheWord(String word) {
        int count = word.length();
        StringBuilder sb = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    private static String takeNewRandomWordFromFile() {

        String filePath = "/Users/yana/IdeaProjects/hangman/src/main/resources/words.txt";
        int numOfWords = countNumsInFile(filePath);
        int randomNum = randomizer(numOfWords);

        String word = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 1; i <= randomNum; i++) {
                word = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Clause for error: " + e.getMessage());
        }

        return word;
    }

    private static int randomizer (int range) {
        int min = 1;
        Random random = new Random();
        return random.nextInt(range - min + 1) + min;
    }

    private static int countNumsInFile (String filePath) {

        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.err.println("Clause for error: " + e.getMessage());
        }

        return count;
    }
    private static void printHangman(int mistakes) {
        switch (mistakes) {
            case 0:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 1:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 2:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 3:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 4:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 5:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" /    |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 6:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" / \\  |");
                System.out.println("      |");
                System.out.println("=========");
                break;
        }
    }
}