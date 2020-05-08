package config;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Config {

	public static final String fileName = "config.properties";
	public static final String POINTS_COLLECT_PER_HEAD_TRUE = "points_collect_per_head_true";
	public static final String POINTS_COLLECT_PER_HEAD_FALSE = "points_collect_per_head_false";
	public static final String POINTS_EARNED_FOR_HEAD = "points_earned_for_head";
	public static final String CODE_LENGTH = "code_length";
	public static final String SHUFFLE_LIST = "shuffle_list";
	public static final String SENTENCES_PATH = "sentences_path";
	public static final String MIN_NUM_OF_PLAYERS = "min_num_of_players";
	
	public static Properties configFile;
	public static InputStream inputStream;
	
	public static void initConfig() {
		configFile = new java.util.Properties();
		inputStream = Config.class.
				getClassLoader().
				getResourceAsStream(fileName);
		try {
			configFile.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
		} catch (Exception eta) {
			eta.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		String value = configFile.getProperty(key);
		return value;
	}
}
