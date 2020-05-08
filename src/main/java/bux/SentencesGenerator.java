package bux;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.lang3.RandomStringUtils;

public class SentencesGenerator {

	private static final String INPUT_FILE = "src/main/resources/sentencesInventory.txt";
	private static final String OUTPUT_FILE = "src/main/resources/sentencesInventoryHeb.txt";
	private static final int N = 15;
	private static final int NUMBER_LINES = 10000;

	public static void main(String[] args) throws IOException {
		
		eraseGershaim();
//		generateRandomEnglishLinesToFile();
	}

	private static void eraseGershaim() throws IOException {
		// TODO Auto-generated method stub
	    try {
	        File myObj = new File(INPUT_FILE);
	        FileWriter fileWriter = new FileWriter(new File(OUTPUT_FILE));
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String line = myReader.nextLine();
	          System.out.println(line);
	          line = line.replaceAll("\"", "");
	          System.out.println(line);
	          fileWriter.write(line + "\n");
	        }
	        myReader.close();
	        fileWriter.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
		
		
	}

	private static void generateRandomEnglishLinesToFile() throws IOException {
		// TODO Auto-generated method stub
		FileWriter fileWriter = new FileWriter(new File(OUTPUT_FILE));
		createLines(fileWriter);
		
		fileWriter.flush();
		fileWriter.close();
		
	}

	private static void createLines(FileWriter fileWriter) throws IOException {

		for (int i = 0; i < NUMBER_LINES; i++) {
			String line = createLine();
			saveLineToFile(fileWriter, line);
		}
	}

	private static String createLine() {

		StringBuilder line = new StringBuilder();

		line.append(RandomStringUtils.randomAlphabetic(1).toUpperCase());
		line.append(RandomStringUtils.randomAlphabetic(N, N * 2).toLowerCase());

		return line.toString();
	}

	private static void saveLineToFile(FileWriter fileWriter, String line) throws IOException {

		fileWriter.write(line);
		fileWriter.write('\n');
	}

}
