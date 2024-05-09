package final_hangman;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman_main {


	    public static ArrayList<String> HighScores = new ArrayList<>();

	    public static void main(String[] args) {

	        System.out.println("Welcome to HANGMAN -by Ronny\n");
	        System.out.println("Choose 'Default' or 'Custom'");
	        Scanner choice = new Scanner(System.in);
	        String userChoice = choice.nextLine();
	        int userScore = 0;
	        //default game
	        if (userChoice.toUpperCase().equals("DEFAULT")) {
	            ReadAndWriteFiles.readDefaultWordsFromFile();
	        
	            System.out.println("Welcome To Ronny's Hangman!");
	            System.out.println("Choose a Level:\n" + "  Hard\n" + "  Normal\n" + "  Easy\n" );
	            String difficulty = choice.nextLine();
	            //get random word from defaultWords ArrayList based on difficulty chosen
	            String defaultWord = HangManGame.chooseWordByDifficulty(difficulty, ReadAndWriteFiles.defaultWords);
	            System.out.println(defaultWord);
	           
	            //begin default game logic
	            ArrayList<Character> playerGuesses = new ArrayList<>();
	            
	            int wrongCount = 0;
	            while(true){
	        
	            HangManGame.printHangedMan(wrongCount);
	            
	            if (wrongCount >= 6) {
	            	System.out.println("Game Over");
	            	System.out.println("Please Enter Name: ");
	            	String userName = choice.nextLine();
	            	ReadAndWriteFiles.writeToHighScores(userName, userScore);
	            	//printOutHighScores();
	            	break;
	            }
	            	
	            	
	            HangManGame.printWordState(defaultWord, playerGuesses);
	            //adds a body part to the hanged man if the wrong character is guessed
	            if(!HangManGame.getPlayerGuess(choice, defaultWord, playerGuesses)) {
	            	wrongCount++;
	            }
	            //if player has won
	            if(HangManGame.printWordState(defaultWord, playerGuesses)){
	            	System.out.println("You Win!");
	            	//add points to playerScore depending on difficulty they were on
	            	System.out.println("+" + HangManGame.distributePoints(difficulty) + " Points!");
	            	userScore += HangManGame.distributePoints(difficulty);
	            	System.out.println("You have " + userScore + " points.");
	            	break;
	            }
	            System.out.println("Please enter your guess for the word: ");
	            if (choice.nextLine().toUpperCase().equals(defaultWord)) {
	            	System.out.println("You Win!");
	            	//add points to playerScore depending on difficulty they were on
	            	System.out.println("+" + HangManGame.distributePoints(difficulty) + " Points!");
	            	userScore += HangManGame.distributePoints(difficulty);
	            	System.out.println("You have " + userScore + " points.");
	            	break;
	            }
	            else {
	            	System.out.println("Nope! Try Again.");
	            }
	            }
	            
	            
	            
	            //got write in and create file algorithms from w3 schools
	      }
	        else if (userChoice.toUpperCase().equals("CUSTOM")) {
	            System.out.println("Are you trying to access or create a Custom game? (type access/create)");
	            String choiceGame = choice.nextLine();
	            if (choiceGame.toUpperCase().equals("ACCESS")) {
	                // Implement access to custom game logic
	                System.out.println("Enter the name of your Custom Game: ");
	                String gameName = choice.nextLine();
	                ReadAndWriteFiles.readCustomWordsFromFile(gameName);
	                String customWord = HangManGame.randomCustomWord(ReadAndWriteFiles.customWords);
	                System.out.println(customWord);
	                
	                ArrayList<Character> playerGuesses = new ArrayList<>();
		            
		            int wrongCount = 0;
		            while(true){
		            	 HangManGame.printHangedMan(wrongCount);
		 	            
		 	            if (wrongCount >= 6) {
		 	            	System.out.println("Game Over");
		 	            	break;
		 	            }
		 	            	
		 	            	
		 	            HangManGame.printWordState(customWord, playerGuesses);
		 	            //adds a body part to the hanged man if the wrong character is guessed
		 	            if(!HangManGame.getPlayerGuess(choice, customWord, playerGuesses)) {
		 	            	wrongCount++;
		 	            }
		            //if player has won
		            if(HangManGame.printWordState(customWord, playerGuesses)){
		            	System.out.println("You Win!");
		            	break;
		            }
		            System.out.println("Please enter your guess for the word: ");
		            if (choice.nextLine().toUpperCase().equals(customWord)) {
		            	System.out.println("You Win!");
		            	break;
		            }
		            else {
		            	System.out.println("Nope! Try Again.");
		            	}
		            }
		            //got write in and create file algorithms from w3 schools
	            
	            } else if (choiceGame.toUpperCase().equals("CREATE")) {
	                System.out.println("What would you like to name your game?");
	                String createGame = choice.nextLine();
	                ReadAndWriteFiles.createFile(createGame);
	                System.out.println("Would you like to add words to this Custom Game? (yes/no)");
	                String contGame = choice.nextLine();
	                if (contGame.equals("yes")) {
	                    ReadAndWriteFiles.writeFile(createGame);
	                }
	            }
	        }
	        choice.close();
	}
	    
}