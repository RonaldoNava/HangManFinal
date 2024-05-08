package final_hangman;
import java.util.ArrayList;
import java.util.Random;

public class HangManGame {

    private String word;
    private int guessesLeft;
    private String difficulty;

    // Constructor
    public HangManGame(String word, int guessesLeft, String difficulty) {
        this.word = word;
        this.guessesLeft = guessesLeft;
        this.difficulty = difficulty;
    }

    // Getter for word
    public String getWord() {
        return word;
    }

    // Setter for word
    public void setWord(String word) {
        this.word = word;
    }

    // Getter for guessesLeft
    public int getGuessesLeft() {
        return guessesLeft;
    }

    // Setter for guessesLeft
    public void setGuessesLeft(int guessesLeft) {
        this.guessesLeft = guessesLeft;
    }
    //ChatGPT helped me construct method
    public static String chooseWordByDifficulty(String difficulty, ArrayList<String> defaultWords) {
        Random random = new Random();
        String word = "";
        if (difficulty.equalsIgnoreCase("Easy")) {
            do {
                word = defaultWords.get(random.nextInt(defaultWords.size()));
            } while (word.length() != 4);
        } else if (difficulty.equalsIgnoreCase("Normal")) {
            do {
                word = defaultWords.get(random.nextInt(defaultWords.size()));
            } while (word.length() != 5);
        } else if (difficulty.equalsIgnoreCase("Hard")) {
            do {
                word = defaultWords.get(random.nextInt(defaultWords.size()));
            } while (word.length() != 6);
        } else {
            System.out.println("Invalid difficulty level. Please choose 'Easy', 'Normal', or 'Hard'.");
        }
        
        return word;
    }
    public static String randomCustomWord(ArrayList<String> customWords) {
    	Random random = new Random();
    	String word = "";
    	word = customWords.get(random.nextInt(customWords.size()));
    	return word;
    	
    }
    }

