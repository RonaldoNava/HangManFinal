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

	public static void writeToHighScores(String userName, int userScore) {
		try (FileWriter myWriter = new FileWriter("HighScores.txt", true)) { // Append mode set to true
			userName = userName.toUpperCase();
			myWriter.write(userName + "," + userScore + "\n");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void readHighScores() {
		try (Scanner input = new Scanner(new File("HighScores.txt"))) {
			while (input.hasNext()) {
				String line = input.nextLine();
				highScores.add(line);
			}
			// Sort highScores based on the scores
			Collections.sort(highScores, (a, b) -> {
				int scoreA = Integer.parseInt(a.split(",")[1]);
				int scoreB = Integer.parseInt(b.split(",")[1]);
				return Integer.compare(scoreB, scoreA); // Descending order
			});
		} catch (FileNotFoundException e) {
			System.out.println("High Score file not found.");
			e.printStackTrace();
		}
	}

	public static void displayHighScores() {
		readHighScores();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("Top 3 High Scores:");
		int count = 0;
		for (String highScore : highScores) {
			if (count >= 3) {
				break; // Stop displaying after the top 3 scores
			}
			String[] parts = highScore.split(",");
			String name = parts[0];
			int score = Integer.parseInt(parts[1]);
			System.out.printf("%s,%02d\n", name, score);
			System.out.println("_______");
			count++;
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
