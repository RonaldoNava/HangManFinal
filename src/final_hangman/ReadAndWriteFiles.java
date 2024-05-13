package final_hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ReadAndWriteFiles {

	public static ArrayList<String> defaultWords = new ArrayList<>();
	public static ArrayList<String> customWords = new ArrayList<>();
	public static ArrayList<String> highScores = new ArrayList<>();

	//will write/store userName accompanied by the userScore to the HighScores .txt file 
	public static void writeToHighScores(String userName, int userScore) {
		try (FileWriter myWriter = new FileWriter("HighScores.txt", true)) { //set to true to append to the .txt file
			myWriter.write(userName + "," + userScore + "\n");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	//transfers the userName accompanied by the userScore into the highScores Arraylist from the HighScores file
	public static void readHighScores() {
		try (Scanner input = new Scanner(new File("HighScores.txt"))) {
			while (input.hasNext()) {
				String line = input.nextLine();
				highScores.add(line);
			}
			// Sort highScores based on the scores
			bubbleSort(highScores); // Descending order
		} catch (FileNotFoundException e) {
			System.out.println("High Score file not found.");
			e.printStackTrace();
		}
	}
	//ChatGPT helped me sort the high scores based on the integer that is displayed after the comma
	private static void bubbleSort(ArrayList<String> highScores) {
	    int n = highScores.size();
	    for (int i = 0; i < n - 1; i++) {
	        for (int j = 0; j < n - i - 1; j++) {
	            int scoreA = Integer.parseInt(highScores.get(j).split(",")[1]);
	            int scoreB = Integer.parseInt(highScores.get(j + 1).split(",")[1]);
	            if (scoreA < scoreB) {
	                // Swap elements at j and j+1
	                String temp = highScores.get(j);
	                highScores.set(j, highScores.get(j + 1));
	                highScores.set(j + 1, temp);
	            }
	        }
	    }
	}
	//displays the top three high scores
	public static void displayHighScores() {
		readHighScores();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Top 3 High Scores:");
		int count = 0;
		for (String highScore : highScores) {
			if (count >= 3) {
				break; //stops displaying after the top 3 scores
			}
			//had ChatGPT help me display both the userName and userScore
			String[] parts = highScore.split(",");
			String name = parts[0];
			int score = Integer.parseInt(parts[1]);
			// Calculate the length of the combined string (name + score + comma)
			int length = name.length() + String.valueOf(score).length() + 3;

			// Display the bar of dashes
			for (int i = 0; i < length; i++) {
			    System.out.print("_");
			}
			System.out.println();
			System.out.printf("%s,%02d\n", name, score);
			count++;
		}
	}
	//creates a file to store the words inputed by the user for their custom game
	//got method from w3schools.com and altered to match game
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
	//gets user input and stores it in a file the user chooses. stops storing words when the user types 'stop'
	//got method from w3schools.com and altered to match game
	public static void writeFile(String userGame) {
		try (Scanner choice = new Scanner(System.in); FileWriter myWriter = new FileWriter(userGame + ".txt", true)) {
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
	//gets words from DefaultWords.txt and adds them to defaultWords ArrayList
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
	//gets words from any game(text file) the user chooses and adds them to customWords ArrayList
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
