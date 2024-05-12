package final_hangman;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Hangman_main {
	
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
	            Stack<Character> incorrectGuesses = new Stack<>();

	            while(true) {
	    			HangManGame.printHangedMan(wrongCount);
	    			//show the incorrect guessed characters in order of how they were guessed.
	    			
	    			if (wrongCount >= 6) {
	    				System.out.println("Game Over");
	    				
	    			}

	    			HangManGame.printWordState(customWord, playerGuesses);
	    			// adds a body part to the hanged man if the wrong character is guessed
	    			if (!HangManGame.printWordState(customWord, playerGuesses)) {
	    				System.out.println("Please enter a letter: ");
	    		        char guess = new Scanner(System.in).next().toUpperCase().charAt(0);
	    		        boolean isCorrectGuess = HangManGame.getPlayerGuess(customWord, playerGuesses, guess);
	    		        if (!isCorrectGuess) {
	    		            wrongCount++;
	    		            incorrectGuesses.push(guess); // Add the incorrect guess to the stack
	    		        }
	    		        
	    			}
	    			// if player has won
	    			if (HangManGame.printWordState(customWord, playerGuesses)) {
	    				System.out.println("You Win!");
	    				break;
	    				
	    				}
	    			//change so that you can only guess the word after the 6 failed guesses as a last resort
	    			System.out.println("Please enter your guess for the word: ");
	    			if (choice.nextLine().toUpperCase().equals(customWord)) {
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
		Stack<Character> incorrectGuesses = new Stack<>();
		
		while(true) {
			HangManGame.printHangedMan(wrongCount);

			if (wrongCount >= 6) {
				System.out.println("Game Over");
				HangManGame.printHS(choice, userScore);
				break;
				// printOutHighScores();
			}
			// adds a body part to the hanged man if the wrong character is guessed
			if (!HangManGame.printWordState(defaultWord, playerGuesses)) {
				System.out.println("Please enter a letter: ");
		        char guess = new Scanner(System.in).next().toUpperCase().charAt(0);
		        //checks whether the user has already guessed that character and if so then discard the guess until user types a character that hasn't been guessed
		        if (playerGuesses.contains(guess)) {
		            System.out.println("'" + guess + "' has already been guessed.");
		        } 
		        else {
		            //add the guess to the list of player guesses if hasn't been guessed before
		            playerGuesses.add(guess);
		            boolean isCorrectGuess = HangManGame.getPlayerGuess(defaultWord, playerGuesses, guess);
		            
		            if (!isCorrectGuess) {
		            	wrongCount++;
		            	incorrectGuesses.push(guess); // Add the incorrect guess to the stack
		            	System.out.println("Incorrect Guesses: " + incorrectGuesses);
		        	} 
		        }
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

			if(wrongCount == 6) {
				System.out.println("Last Chance!\nPlease enter your guess for the word: ");
			
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
	
}