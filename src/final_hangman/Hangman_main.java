package final_hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class Hangman_main {

	// add a stack that shows the user already guessed characters in red

	public static void main(String[] args) {
		System.out.println("Welcome to HANGMAN -by Ronny\n");
		System.out.println("Choose 'Default' or 'Custom'");

		Scanner choice = new Scanner(System.in);
		String userChoice = choice.nextLine();
		int userScore = 0;
		// needs the player to enter either 'default' or 'custom' before continuing
		while (!userChoice.toUpperCase().equals("DEFAULT") && !userChoice.toUpperCase().equals("CUSTOM")) {
			System.out.println("Please enter 'Default' or 'Custom'.");
			userChoice = choice.nextLine();
		}
		if (userChoice.toUpperCase().equals("DEFAULT")) {
			defaultGame(choice, userScore);
		} else if (userChoice.toUpperCase().equals("CUSTOM")) {
			customGame(choice);
		}

		choice.close();
	}

	private static void customGame(Scanner choice) {
	    System.out.println("Are you trying to access or create a Custom game? (type access/create)");
	    String choiceGame = choice.nextLine();
	   while(!choiceGame.equalsIgnoreCase("ACCESS") && !choiceGame.equalsIgnoreCase("CREATE")) {
		   System.out.println("Please type 'Access' or 'Create'");
		   choiceGame = choice.nextLine();
	   }
		   
	    if (choiceGame.equalsIgnoreCase("ACCESS")) {
	        // Implement access to custom game logic
	        System.out.println("Enter the name of your Custom Game: ");
	        String gameName = choice.nextLine();
	        ReadAndWriteFiles.readCustomWordsFromFile(gameName);
	            String customWord = HangManGame.randomCustomWord(ReadAndWriteFiles.customWords);
	            System.out.println(customWord);
	            
	            ArrayList<Character> playerGuesses = new ArrayList<>();
	            int wrongCount = 0;
	            while(true) {
	    			HangManGame.printHangedMan(wrongCount);

	    			if (wrongCount >= 6) {
	    				System.out.println("Game Over");
	    				
	    			}

	    			HangManGame.printWordState(customWord, playerGuesses);
	    			// adds a body part to the hanged man if the wrong character is guessed
	    			if (!HangManGame.getPlayerGuess(choice, customWord, playerGuesses)) {
	    				wrongCount++;
	    				
	    			}
	    			// if player has won
	    			if (HangManGame.printWordState(customWord, playerGuesses)) {
	    				// add points to playerScore depending on difficulty they were on
	    				System.out.println("You Win!");
	    				break;
	    				
	    				}

	    			System.out.println("Please enter your guess for the word: ");
	    			if (choice.nextLine().toUpperCase().equals(customWord)) {
	    				// add points to playerScore depending on difficulty they were on
	    				System.out.println("You Win!");
	    				break;
	    			} 
	    			else {
	    				System.out.println("Incorrect Word");
	    				
	    			}
	            }
	    	} 
	    else if (choiceGame.equalsIgnoreCase("CREATE")) {
	        System.out.println("What would you like to name your game?");
	        String createGame = choice.nextLine();
	        ReadAndWriteFiles.createFile(createGame);
	        System.out.println("Would you like to add words to this Custom Game? (yes/no)");
	        String contGame = choice.nextLine();
	        if (contGame.equalsIgnoreCase("yes")) {
	            ReadAndWriteFiles.writeFile(createGame);
	        }
	    }
	    System.out.println("Please type 'Access' or 'Create'");
	}
	
	private static void defaultGame(Scanner choice, int userScore) {
		ReadAndWriteFiles.readDefaultWordsFromFile();

		System.out.println("Welcome To Ronny's Hangman!");
		
		
			
		System.out.println("Choose a Level:\n" + "  Hard\n" + "  Normal\n" + "  Easy\n");
		String difficulty = choice.nextLine();
		
		while(!difficulty.equalsIgnoreCase("EASY") && !difficulty.equalsIgnoreCase("NORMAL") && !difficulty.equalsIgnoreCase("HARD")) {
			   System.out.println("Please type 'Easy', 'Normal, or 'Hard'.");
			   difficulty = choice.nextLine();
		   }

		
		//get random word from defaultWords ArrayList based on difficulty chosen
		String defaultWord = HangManGame.chooseWordByDifficulty(difficulty, ReadAndWriteFiles.defaultWords);
		System.out.println(defaultWord);

		// begin default game logic
		ArrayList<Character> playerGuesses = new ArrayList<>();

		int wrongCount = 0;
		while(true) {
			HangManGame.printHangedMan(wrongCount);

			if (wrongCount >= 6) {
				System.out.println("Game Over");
				HangManGame.printHS(choice, userScore);
				break;
				// printOutHighScores();
			}

			HangManGame.printWordState(defaultWord, playerGuesses);
			// adds a body part to the hanged man if the wrong character is guessed
			if (!HangManGame.getPlayerGuess(choice, defaultWord, playerGuesses)) {
				wrongCount++;
				
			}
			// if player has won
			if (HangManGame.printWordState(defaultWord, playerGuesses)) {
				
				// add points to playerScore depending on difficulty they were on
				System.out.println("You Win! " + "+" + HangManGame.distributePoints(difficulty) + " Points!");
				userScore += HangManGame.distributePoints(difficulty);
				System.out.println("You have " + userScore + " points.");
				HangManGame.printHS(choice, userScore);
				break;
			}

			System.out.println("Please enter your guess for the word: ");
			if (choice.nextLine().toUpperCase().equals(defaultWord)) {
				
				// add points to playerScore depending on difficulty they were on
				System.out.println("You Win! " + "+" + HangManGame.distributePoints(difficulty) + " Points!");
				userScore += HangManGame.distributePoints(difficulty);
				System.out.println("You have " + userScore + " points.");
				HangManGame.printHS(choice, userScore);
				break;
			} 
			else {
				System.out.println("Incorrect Word");
				
			}
		}
	}

	
}
	

