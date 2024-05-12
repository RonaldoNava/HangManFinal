package final_hangman;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangManGame { 
	
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
    //returns points to players if game is won depending on difficulty chosen
    public static int distributePoints(String difficulty){
    	String userDifficulty = difficulty.toUpperCase();
		if (userDifficulty.equals("EASY")) {
			return 3;
		}
		else if (userDifficulty.equals("NORMAL")) {
			return 4;
		}
		else if (userDifficulty.equals("HARD")) {
			return 6;
		}
		return 0;
		}
    	
    //returns true if the users guess is found in the word
    public static boolean getPlayerGuess(String defaultWord, ArrayList<Character> playerGuesses, char guess) {
    	playerGuesses.add(Character.toUpperCase(guess));
        return defaultWord.contains(Character.toString(Character.toUpperCase(guess)));
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
    public static void printHangedMan(int wrongCount) {
		System.out.println(" -------");
		System.out.println(" |     |");
		if (wrongCount >= 1)
			System.out.println(" O");
		if (wrongCount >= 2) {
			System.out.print("\\ ");
			if (wrongCount >= 3) {
				System.out.println("/");
			}
			else {
				System.out.println("");
			}
		}
		if (wrongCount >= 4)
			System.out.println(" |");
		if (wrongCount >= 5) {
			System.out.print("/ ");
			if (wrongCount >= 6) {
				System.out.println("\\");
			}
			else {
				System.out.println("");
			}
		}
		System.out.println("");
		System.out.println("");
	}
    public static void printHS(Scanner choice, int userScore) {
		System.out.println("Please Enter Name: ");
		String userName = choice.nextLine();
		ReadAndWriteFiles.writeToHighScores(userName, userScore);
		ReadAndWriteFiles.displayHighScores();
	}
}