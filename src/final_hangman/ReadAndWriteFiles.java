package final_hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAndWriteFiles {
	
	public static ArrayList<String> defaultWords = new ArrayList<>();
    public static ArrayList<String> customWords = new ArrayList<>();
    public static ArrayList<String> HighScores = new ArrayList<>();
    
		public static void writeToHighScores(String userName, int userScore)  {
	    	try (Scanner choice = new Scanner(System.in);
		             FileWriter myWriter = new FileWriter("HighScores.txt")) {
		                userName = userName.toUpperCase();
		                myWriter.write(userName +  ","+ userScore +"\n");
		               
		            }catch (IOException e) {
			            System.out.println("An error occurred.");
			            e.printStackTrace();
		            }
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
