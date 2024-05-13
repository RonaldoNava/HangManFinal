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
		//runs default game 
		if (userChoice.toUpperCase().equals("DEFAULT")) {
			defaultGame(choice, userScore);
		}
		//runs custom game
		else if (userChoice.toUpperCase().equals("CUSTOM")) {
			customGame(choice);
		}

		choice.close();
	}

	private static void customGame(Scanner choice) {
		//asks whether the user wants to create a custom game or access an already existing custom game
	    System.out.println("Are you trying to access or create a Custom game? (type access/create)");
	    String choiceGame = choice.nextLine();
	   while(!choiceGame.equalsIgnoreCase("ACCESS") && !choiceGame.equalsIgnoreCase("CREATE")) {
		   System.out.println("Please type 'Access' or 'Create'");
		   choiceGame = choice.nextLine();
	   }
		   
	    if (choiceGame.equalsIgnoreCase("ACCESS")) {
	        // Implement access to custom game logic
	        System.out.println("Enter the name of your Custom Game: ");
	        //loads words from the custom game text file into the customWords ArrayList
	        String gameName = choice.nextLine();
	        ReadAndWriteFiles.readCustomWordsFromFile(gameName);
	        	//gets a random custom word from the customWords ArrayList
	            String customWord = HangManGame.randomCustomWord(ReadAndWriteFiles.customWords);
	            System.out.println(customWord);
	            //ArrayList of all guessed characters
	            ArrayList<Character> playerGuesses = new ArrayList<>();
	            //keeps track of incorrect player guesses as well as stores it in a stack.
	            int wrongCount = 0;
	            Stack<Character> incorrectGuesses = new Stack<>();

	            while(true) {
	            	//adds a body part to the hanged man if the wrong character is guessed
	            	HangManGame.printHangedMan(wrongCount);
	            	//ends game if the user has 6 incorrect guesses and doesn't guess the word
	    			if (wrongCount >= 6) {
	    				System.out.println("Game Over");
	    				break;
	    			}
	    			//prints out dashes for unguessed characters and replaces the dash with the correct character if the character is guessed.
	    			//allows user to input a guess as long as the game is not over
	    			if (!HangManGame.printWordState(customWord, playerGuesses)) {
	    				System.out.println("Please enter a letter: ");
	    		        char guess = new Scanner(System.in).next().toUpperCase().charAt(0);
	    		        //checks whether the user has already guessed that character and if so then discard the guess until user types a character that hasn't been guessed
	    		        if (playerGuesses.contains(guess)) {
	    		            System.out.println("'" + guess + "' has already been guessed.");
	    		        } 
	    		        else {
	    		            //add the guess to the list of player guesses if hasn't been guessed before
	    		            playerGuesses.add(guess);
	    		            //sets isCorrectGuess to true if the users guess is found in the word.
	    		            boolean isCorrectGuess = HangManGame.getPlayerGuess(customWord, playerGuesses, guess);
	    		            //adds 1 to wrongCount if player guesses incorrectly which adds a body part to the hanged man
	    		            if (!isCorrectGuess) {
	    		            	wrongCount++;
	    		            	incorrectGuesses.push(guess); //adds the incorrect guess to the stack
	    		            	System.out.println("Incorrect Guesses: " + incorrectGuesses); //displays stack from most recent incorrect guess to least recent
	    		        	} 
	    		        }
	    			}
	    			//if all characters are guessed before the game is over, then the user wins
	    			if (HangManGame.printWordState(customWord, playerGuesses)) {
	    				System.out.println("You Win!");
	    				break;
	    			}
	    			//gives the user a final chance to guess what the word is if they have the complete hanged man
	    			if(wrongCount == 6) {
	    				System.out.println("Last Chance!\nPlease enter your guess for the word: ");
	    			
	    			if (choice.nextLine().toUpperCase().equals(customWord)) {
	    				System.out.println("You Win!");
	    				break;
	    			} 
	    			else {
	    				System.out.println("Incorrect Word");
	    				
	    			}
	            }
	    	}
	    }
	    //if a player decides to create their own custom game with their own word bank
	    else if (choiceGame.equalsIgnoreCase("CREATE")) {
	        System.out.println("What would you like to name your game?"); 
	        //creates the file with the name of the user input
	        String createGame = choice.nextLine();
	        ReadAndWriteFiles.createFile(createGame);
	        //adds words to that newly created .txt file if they choose to
	        //also able to add words to already existing .txt files
	        System.out.println("Would you like to add words to this Custom Game? (yes/no)");
	        String contGame = choice.nextLine();
	        if (contGame.equalsIgnoreCase("yes")) {
	            ReadAndWriteFiles.writeFile(createGame);
	        }
	    }
	    System.out.println("Please type 'Access' or 'Create'");
	}
	
	private static void defaultGame(Scanner choice, int userScore) {
		//puts in all of the words from a 1000-word .txt file into the defaultWords ArrayList
		ReadAndWriteFiles.readDefaultWordsFromFile();

		System.out.println("Welcome To Ronny's Hangman!");	
		System.out.println("Choose a Level:\n" + "  Hard\n" + "  Normal\n" + "  Easy\n");
		//allows the user to choose between 3 difficulties
		String difficulty = choice.nextLine();
		while(!difficulty.equalsIgnoreCase("EASY") && !difficulty.equalsIgnoreCase("NORMAL") && !difficulty.equalsIgnoreCase("HARD")) {
			   System.out.println("Please type 'Easy', 'Normal, or 'Hard'.");
			   difficulty = choice.nextLine();
		   }

		
		//get random word from defaultWords ArrayList based on difficulty chosen
		String defaultWord = HangManGame.chooseWordByDifficulty(difficulty, ReadAndWriteFiles.defaultWords);
		System.out.println(defaultWord);

		//begin default game logic
		ArrayList<Character> playerGuesses = new ArrayList<>();
		int wrongCount = 0;
		Stack<Character> incorrectGuesses = new Stack<>();
		
		while(true) {
			//adds a body part to the hanged man if the wrong character is guessed
			HangManGame.printHangedMan(wrongCount);

			if (wrongCount >= 6) {
				System.out.println("Game Over");
				//gets username and userscore and puts it into the HighScores.txt file and then displays the top 3 scores
				HangManGame.printHS(choice, userScore);
				break;
				
			}
			//prints out dashes for unguessed characters and replaces the dash with the correct character if the character is guessed.
			//allows user to input a guess as long as the game is not over
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
		            //sets isCorrectGuess to true if the users guess is found in the word.
		            boolean isCorrectGuess = HangManGame.getPlayerGuess(defaultWord, playerGuesses, guess);
		            //adds the incorrect guess to the stack
		            if (!isCorrectGuess) {
		            	wrongCount++;
		            	incorrectGuesses.push(guess); //adds the incorrect guess to the stack
		            	//displays stack from most recent incorrect guess to least recent
		            	System.out.println("Incorrect Guesses: " + incorrectGuesses);
		        	} 
		        }
			}
			//if all characters are guessed before the game is over, then the user wins
			if (HangManGame.printWordState(defaultWord, playerGuesses)) {
				
				// add points to playerScore depending on difficulty they were on
				System.out.println("You Win! " + "+" + HangManGame.distributePoints(difficulty) + " Points!");
				userScore += HangManGame.distributePoints(difficulty);
				System.out.println("You have " + userScore + " points.");
				HangManGame.printHS(choice, userScore);
				break;
			}
			//gives the user a final chance to guess what the word is if they have the complete hanged man
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