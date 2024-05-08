package final_hangman;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hangman_main {

	 public static ArrayList<String> defaultWords = new ArrayList<>();
	    public static ArrayList<String> customWords = new ArrayList<>();

	    public static void main(String[] args) {

	        System.out.println("Welcome to HANGMAN -by Ronny\n");
	        System.out.println("Choose 'Default' or 'Custom'");
	        Scanner choice = new Scanner(System.in);
	        String userChoice = choice.nextLine();
	        
	        if (userChoice.equals("Default")) {
	            readDefaultWordsFromFile();
	        
	            System.out.println("Welcome To Ronny's Hangman!");
	            System.out.println("Choose a Level:\n" + "  Hard\n" + "  Normal\n" + "  Easy\n" );
	            String difficulty = choice.nextLine();
	            System.out.println(HangManGame.chooseWordByDifficulty(difficulty, defaultWords));
	            // Implement default game logic

	            //got write in and create file algorithms from w3 schools
	        }
	        else if (userChoice.equals("Custom")) {
	            System.out.println("Are you trying to access or create a Custom game? (type access/create)");
	            String choiceGame = choice.nextLine();
	            if (choiceGame.equals("access")) {
	                // Implement access to custom game logic
	                System.out.println("Enter the name of your Custom Game: ");
	                String gameName = choice.nextLine();
	                readCustomWordsFromFile(gameName);
	                System.out.println(HangManGame.randomCustomWord(customWords));
	            
	            } else if (choiceGame.equals("create")) {
	                System.out.println("What would you like to name your game?");
	                String createGame = choice.nextLine();
	                createFile(createGame);
	                System.out.println("Would you like to add words to this Custom Game? (yes/no)");
	                String contGame = choice.nextLine();
	                if (contGame.equals("yes")) {
	                    writeFile(createGame);
	                }
	            }
	        }
	        choice.close();
	}

	    public static void createFile(String userGame) {
	        try {
	            File myObj = new File(userGame + ".txt");
	            if (myObj.createNewFile()) {
	                System.out.println("Game created: " + myObj.getName());
	            } else {
	                System.out.println("Custom Game already exists.");
	            }
	        } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }
	    }

	    public static void writeFile(String userGame) {
	        try (Scanner choice = new Scanner(System.in);
	             FileWriter myWriter = new FileWriter(userGame + ".txt", true)) {
	            System.out.println("Enter words for the custom game (type 'stop' to finish):");
	            while (true) {
	                String writeWords = choice.nextLine();
	                if (writeWords.equals("stop")) {
	                    break;
	                }
	                writeWords = writeWords.toUpperCase();
	                myWriter.write(writeWords + "\n");
	            }
	            System.out.println("Words added to the custom game.");
	        } catch (IOException e) {
	            System.out.println("An error occurred.");
	            e.printStackTrace();
	        }
	    }
	    public static void readDefaultWordsFromFile() {
	        try (Scanner input = new Scanner(new File("DefaultWords.txt"))) {
	            
	            while (input.hasNext()) {
	            	String word = input.nextLine().toUpperCase();
	                defaultWords.add(word); 
	            }
	        } catch (FileNotFoundException e) {
	            System.out.println("Default words file not found.");
	            e.printStackTrace();
	        }
	    }
	    public static void readCustomWordsFromFile(String gameName) {
	        try (Scanner input = new Scanner(new File(gameName + ".txt"))) {
	        	
	            while (input.hasNext()) {
	            	String word = input.nextLine().toUpperCase();
	                customWords.add(word);
	            }
	        } catch (FileNotFoundException e) {
	            System.out.println("Custom game file not found.");
	            e.printStackTrace();
	        }
	}
}
