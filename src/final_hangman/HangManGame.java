package final_hangman;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
    public static int distributePoints(String difficulty){
		if (difficulty.equals("Easy")) {
			return 3;
		}
		else if (difficulty.equals("Normal")) {
			return 4;
		}
		else if (difficulty.equals("Hard")) {
			return 6;
		}
		return 0;
		}
    
    public static void getPlayerGuess(Scanner choice, String defaultWord, ArrayList<Character> playerGuesses) {
		System.out.println("Please enter a letter: ");
		String letterGuess = choice.nextLine();
		letterGuess = letterGuess.toUpperCase();
		playerGuesses.add(letterGuess.charAt(0));
	}
    public static boolean printWordState(String defaultWord, ArrayList<Character> playerGuesses) {
		int correctCount = 0;
		for (int i = 0; i < defaultWord.length(); i++) {
			if (playerGuesses.contains(defaultWord.charAt(i))){
				System.out.print(defaultWord.charAt(i));
				correctCount++;
			} 
			else {
				System.out.print("-");
			}
		}
		System.out.println();
		return(defaultWord.length() == correctCount);
	}
}