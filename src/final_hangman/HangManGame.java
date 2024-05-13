package final_hangman;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangManGame { 
	
    public static String chooseWordByDifficulty(String difficulty, ArrayList<String> defaultWords) {
        Random random = new Random();
        String word = "";//initialize word
        //get a random 4 letter word from defaultWords ArrayList
        //ChatGPT helped write if-statement
        if (difficulty.equalsIgnoreCase("Easy")) {
        	//keeps looking for word until a random 4 letter word is found (do-while loop)
            do {
                word = defaultWords.get(random.nextInt(defaultWords.size()));
            } 
            while (word.length() != 4);
        } 
      //get a random 5 letter word from defaultWords ArrayList
        else if (difficulty.equalsIgnoreCase("Normal")) {
        	//keeps looking for word until a random 5 letter word is found 
            do {
                word = defaultWords.get(random.nextInt(defaultWords.size()));
            } 
            while (word.length() != 5);
        }
      //get a random 6 letter word from defaultWords ArrayList
        else if (difficulty.equalsIgnoreCase("Hard")) {
        	//keeps looking for word until a random 6 letter word is found 
            do {
                word = defaultWords.get(random.nextInt(defaultWords.size()));
            } 
            while (word.length() != 6);
        }
        //prompts user to choose a valid difficulty level
        else {
            System.out.println("Invalid difficulty level. Please choose 'Easy', 'Normal', or 'Hard'.");
        }
        //returns random word based on user difficulty
        return word;
    }
    //returns a random word from customWords ArrayList
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
    //prints out dashes for unguessed characters and replaces the dashes with the correct character if player guesses it correctly
    //youtube tutorial on how to do this
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
    //prints out a body part if the user guesses a character that is not in the word to be guessed
    //also got from youtube tutorial
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
    //prompts user to enter name to enter into HighScores file
    //Updates and outputs current top 3 high scores along with names
    public static void printHS(Scanner choice, int userScore) {
		System.out.println("Please Enter Name: ");
		String userName = choice.nextLine();
		ReadAndWriteFiles.writeToHighScores(userName, userScore);
		ReadAndWriteFiles.displayHighScores();
	}
}