package app;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import logic.PlayerPersonalInfo;

public class InfoGenerator {
	
	public static boolean generateDecision() {
		// TODO Auto-generated method stub
		return new Random().nextInt(100) < TestApplicationByPlayers.CHANCE_TO_CONTINUE ? true : false;
	}

	public static String mapToString(Map<PlayerPersonalInfo, Integer> scores) {
		// TODO Auto-generated method stub

		StringBuilder str = new StringBuilder();
		for (Map.Entry<PlayerPersonalInfo, Integer> score : scores.entrySet()) {
			str.append(score.getKey().getName() + ": " + score.getValue().toString() + "\n");
		}
		return str.toString();
	}

	public static String guessSentence(List<String> falses) {
		// TODO Auto-generated method stub
		return falses.get(new Random().nextInt(falses.size()));
	}
	
	public static Image generateImage() {
		// TODO Auto-generated method stub

		BufferedImage image = new BufferedImage(TestApplicationByPlayers.IMAGE_WIDTH,
				TestApplicationByPlayers.IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

		paintRandom(image);

		return image;
	}

	private static void paintRandom(BufferedImage image) {
		// TODO Auto-generated method stub

		Random rand = new Random();
		int bound = 0xFFFFFF;

		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				image.getRaster().setDataElements(j, i,
						image.getColorModel().getDataElements(rand.nextInt(bound) | 0xF0000000, null));
			}

		}

	}

	public static String generateName() {
		// TODO Auto-generated method stub
		StringBuilder name = new StringBuilder();

		name.append(RandomStringUtils.randomAlphabetic(1).toUpperCase());
		name.append(RandomStringUtils.randomAlphabetic(5, 10).toLowerCase());

		return name.toString();
	}

	public static String generateFalseSentence() {
		// TODO Auto-generated method stub

		StringBuilder sentence = new StringBuilder();

		sentence.append(RandomStringUtils.randomAlphabetic(15, 20).toLowerCase());

		return sentence.toString();
	}

	public static long generateId() {
		// TODO Auto-generated method stub
		return Long.parseLong(RandomStringUtils.randomNumeric((TestApplicationByPlayers.ID_LENGTH)));
	}


}
