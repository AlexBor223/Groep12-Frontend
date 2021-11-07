package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameRandomiser {
    private Random random;
    private final char[] characters;

    /**
     * GameRandomiser constructor
     * @author Plinio
     */
    public GameRandomiser() {
        random = new Random();
        characters = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }

    /**
     * Shuffles string characters
     * @param input
     * @return Shuffled String
     * @author Plinio
     */
    private String shuffleString(String input) {
        ArrayList<Character> shuffleArray = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            shuffleArray.add(input.charAt(i));
        }

        Collections.shuffle(shuffleArray);
        StringBuilder result = new StringBuilder(shuffleArray.size());

        for (int i = 0; i < input.length(); i++) {
            result.append(shuffleArray.get(i));
        }

        return result.toString();
    }

    /**
     * Gets a randomised string with one correct character and the rest incorrect
     * @param correctChar
     * @param numOfIncorrect
     * @return Randomised String that's shuffled
     * @author Plinio
     */
    private String getRandomisedString(String correctChar, int numOfIncorrect) {
        StringBuilder result = new StringBuilder(correctChar);

        for (int i = 1; i < numOfIncorrect; i++) {
            char randomChar = characters[random.nextInt(characters.length)];
            result.append(randomChar);
        }

        return shuffleString(result.toString());
    }

    /**
     * Gets an ArrayList of randomised Strings
     * @param letters
     * @param numOfButtons
     * @return ArrayList of Strings
     * @author Plinio
     */
    public ArrayList<String> getStringsFromLetters(String letters, int numOfButtons) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < letters.length(); i++) {
            result.add(getRandomisedString(String.valueOf(letters.charAt(i)), numOfButtons));
        }

        return result;
    }
}
