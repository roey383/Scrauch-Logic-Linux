package logic;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.RandomStringUtils;

import config.Config;

public class GamesRepositoryManager {
	
	private Map<String, Game> codeToGame;
	private SentencesInventory sentencesInventory;
	
	public GamesRepositoryManager() throws IOException {
		// TODO Auto-generated constructor stub
		this.codeToGame = new ConcurrentHashMap<String, Game>();  
		this.sentencesInventory = new SentencesInventory(Config.getProperty(Config.SENTENCES_PATH), 
				Boolean.parseBoolean(Config.getProperty(Config.SHUFFLE_LIST)));
	}

	public synchronized String createNewGame(int numOfPLayers, int numOfSessions) {
		// TODO Auto-generated method stub
		
		String code = generateGameCode(Integer.parseInt(Config.getProperty(Config.CODE_LENGTH)));
		Game game = new Game(code, numOfPLayers, numOfSessions, new SentencesInventoryRetriver(sentencesInventory));
		codeToGame.put(code, game);
		return code;
	}

	private String generateGameCode(int length) {
		// TODO Auto-generated method stub
		
		String code = RandomStringUtils.randomAlphanumeric(length).toUpperCase();
		
		while (codeToGame.containsKey(code)) {
			code = RandomStringUtils.randomAlphanumeric(length).toUpperCase();
		}
		
		return code;
	}

	public Game getGame(String gameCode) {
		// TODO Auto-generated method stub
		return codeToGame.get(gameCode);
	}


	

}
