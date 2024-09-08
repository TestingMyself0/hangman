package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    private static String wordDisplay = "";
    private static Integer mistakesCount = 0;

    private static List<String> usedLetters = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Добро пожаловать в игру! Нажмите [Н]ачать или [В]ыйти");
            String line = scanner.next();
            startTheGame(line);

            String word = takeNewRandomWordFromFile();
            System.out.println("Кол-во букв в слове - " + word.length());

            wordDisplay = starsLettersInTheWord(word);
            System.out.println(wordDisplay);

            do {
                checkInputLetterInTheWord(inputValidLetter(scanner), word);
                System.out.println(wordDisplay);

                if (mistakesCount > 0)
                    printHangman(mistakesCount);
                if (!wordDisplay.contains("*")){
                    System.out.println("\nВы выиграли! Поздравляю!!!");
                    return;
                }

                System.out.println("Использованные буквы: " + usedLetters);
            } while (mistakesCount < 6);

            System.out.println("\nВы проиграли! Попробуйте еще раз.");
        }

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

        Path currentDir = Paths.get("").toAbsolutePath();
        String filePath = currentDir.resolve("src/main/resources/words.txt").toString();
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
        Random random = new Random();
        return random.nextInt(range) + 1;
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