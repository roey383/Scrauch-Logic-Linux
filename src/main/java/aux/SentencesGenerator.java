package aux;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.lang3.RandomStringUtils;

public class SentencesGenerator {

	private static final String INPUT_FILE = "src/main/resources/sentencesInventoryWiki.txt";
	private static final String OUTPUT_FILE = "src/main/resources/sentencesInventoryTemp.txt";
	private static final int N = 15;
	private static final int NUMBER_LINES = 10000;

	public static void main(String[] args) throws IOException {

		eraseGershaim();
//		generateRandomEnglishLinesToFile();
	}

	private static void eraseGershaim() throws IOException {
		// TODO Auto-generated method stub
		int count = 0;
		int count1 = 0;
		try {
			File myObj = new File(INPUT_FILE);
			FileWriter fileWriter = new FileWriter(new File(OUTPUT_FILE));
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				if (line.startsWith("מקור")) {
					count1++;
//					System.out.println(line);
					continue;
				}
				if (line.startsWith("במקור")) {
					count1++;
//					System.out.println(line);
					continue;
				}
				if (line.startsWith("המקור")) {
					count1++;
//					System.out.println(line);
					continue;
				}
				if (line.startsWith("פירוש")) {
					count1++;
//					System.out.println(line);
					continue;
				}
				if (line.startsWith("פרוש")) {
					count1++;
//					System.out.println(line);
					continue;
				}
				if (line.length() < 3) {
					count1++;
//					System.out.println(line);
					continue;
				}
				if (line.length() > 50) {
					count1++;
					System.out.println(line);
					continue;
				}
				if (line.contains("(")) {
//					count++;
//					System.out.println(line);
					int start = line.indexOf("(");
					int end = line.indexOf(")");
//					System.out.println(line.substring(0,start)+line.substring(end+1));
					line = line.substring(0,start)+line.substring(end+1);
//					line = line.substring(0, line.length()-1);
//					System.out.println(line);
				}
				if (line.contains("~")) {
//					count++;
					System.out.println(line);
					System.out.println(line.split("~")[0]);
					line = line.split("~")[0];
//					line = line.substring(0,start)+line.substring(end+1);
//					line = line.substring(0, line.length()-1);
//					System.out.println(line);
				}
				if (line.endsWith(" ")) {
//					count++;
//					System.out.println(line);
					line = line.substring(0, line.length()-1);
//					System.out.println(line);
				}
				if (line.endsWith(".")) {
//					count++;
//					System.out.println(line);
					line = line.substring(0, line.length()-1);
//					System.out.println(line);
				}
				count++;
				fileWriter.write(line + "\n");
			}
			myReader.close();
			fileWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		System.out.println(count);
		System.out.println(count1);

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
