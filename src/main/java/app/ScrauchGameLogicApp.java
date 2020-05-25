package app;

import java.awt.Image;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import config.Config;
import logic.DrawingTrueSentencePair;
import logic.Game;
import logic.GamesRepositoryManager;
import logic.Player;
import logic.PlayerPersonalInfo;
import logic.PlayersRepository;
import logic.Result;

public class ScrauchGameLogicApp implements ScrauchLogicServiceAPI {

	final static Logger logger = Logger.getLogger(ScrauchGameLogicApp.class);

	private GamesRepositoryManager gamesRepository;
	private PlayersRepository playersRepository;

	public ScrauchGameLogicApp() throws IOException {
		super();
		Config.initConfig();
		this.gamesRepository = new GamesRepositoryManager();
		this.playersRepository = new PlayersRepository();
	}

//	
	@Override
	public String newGame(int numOfPLayers, int numOfSessions) {
		// TODO Auto-generated method stub

		String code = gamesRepository.createNewGame(numOfPLayers, numOfSessions);
		return code;
	}

//	
	@Override
	public synchronized int joinPlayerToGame(long playerId, String gameCode) {
		// TODO Auto-generated method stub

		Game game = gamesRepository.getGame(gameCode);
		if (game == null) {
			return -2;
		}
		if (game.getNumOfPlayers() - game.getNumOfLoggedInPlayers() == 0) {
			return -1;
		}
		game.addPlayer(playerId);
		playersRepository.addPlayer(game.getPlayer(playerId));
		int numberOfPlayersLeftToJoin = game.getNumOfPlayers() - game.getNumOfLoggedInPlayers();

		return numberOfPlayersLeftToJoin;
	}

//	
	@Override
	public int registerPlayerInformation(long playerId, String playerName, Image selfPortrait) {
		// TODO Auto-generated method stub

		Player player = playersRepository.getPlayer(playerId);
		player.addInfo(playerName, selfPortrait);
		Game game = player.getGame();
		int numberOfPlayersLeftToRegisterInfo = game.getNumOfPlayers() - game.getNumOfPlayersAlreadyRegisteredInfo();

		return numberOfPlayersLeftToRegisterInfo;
	}

//	
	@Override
	public List<PlayerPersonalInfo> getAllPlayersInformation(String gameCode) {
		// TODO Auto-generated method stub

		return gamesRepository.getGame(gameCode).getPlayersInfo();

	}

//	
	@Override
	public String getNextTrueSentence(String gameCode) {
		// TODO Auto-generated method stub

		Game game = gamesRepository.getGame(gameCode);
		return game.getNextTrueSentence();
	}

//	
	@Override
	public int addPlayerDrawing(long playerId, Image drawing, String trueSentence) {
		// TODO Auto-generated method stub

		Player player = playersRepository.getPlayer(playerId);
		player.addDrawing(drawing, trueSentence);
		Game game = player.getGame();

		return game.getNumberOfPlayersLeftToDraw();
	}

//	
	@Override
	public DrawingTrueSentencePair getCurrentDrawingSentencePlayer(String gameCode) {
		// TODO Auto-generated method stub
		Game game = gamesRepository.getGame(gameCode);
		return game.getCurrentDrawingSentencePlayer();
	}

//
	@Override
	public int addPlayerFalseDiscriptionToCurrentDrawing(long playerId, String falseSentence) {
		// TODO Auto-generated method stub

		Player player = playersRepository.getPlayer(playerId);
		player.addFalseSentenceToCurrentDrawing(falseSentence);
		Game game = player.getGame();

		return game.getNumberOfPlayersLeftToWriteFalseSentence();
	}

//
	@Override
	public List<String> getAllSentencesToCurrentDrawing(String gameCode) {
		// TODO Auto-generated method stub
		return gamesRepository.getGame(gameCode).getAllSentencesToCurrentDrawing();
	}

//
	@Override
	public List<String> getAllSentencesToCurrentDrawingExceptFalser(long playerId) {
		// TODO Auto-generated method stub
		return playersRepository.getPlayer(playerId).getGame().getAllSentencesToCurrentDrawingExceptFalser(playerId);
	}

//
	@Override
	public int addPlayerGuessToCurrentDrawing(long playerId, String guessSentence) {
		// TODO Auto-generated method stub

		Player player = playersRepository.getPlayer(playerId);
		player.addGuessSentenceToCurrentDrawing(guessSentence);
		Game game = player.getGame();

		return game.getNumberOfPlayersLeftToWriteGuessSentence();
	}

//	
	@Override
	public List<Result> getCurrentDrawingResults(String gameCode) {
		// TODO Auto-generated method stub

		Game game = gamesRepository.getGame(gameCode);
		return game.getCurrentDrawingResults();
	}

//	
	@Override
	public String getPlayerCurrentFalse(long playerId) {
		// TODO Auto-generated method stub

		Player player = playersRepository.getPlayer(playerId);
		return player.getCurrentFalseSentence();
	}

//
	@Override
	public Map<PlayerPersonalInfo, Integer> getLastRoundScoreBoard(String gameCode) {
		// TODO Auto-generated method stub

		Game game = gamesRepository.getGame(gameCode);
		return game.getLastRoundScoreBoard();
	}

//
	@Override
	public Map<PlayerPersonalInfo, Integer> getTotalScoreBoard(String gameCode) {
		// TODO Auto-generated method stub

		Game game = gamesRepository.getGame(gameCode);
		return game.getTotalScoreBoard();
	}

	@Override
	public int addPlayerSawScores(long playerId) {
		// TODO Auto-generated method stub
		Player player = playersRepository.getPlayer(playerId);
		player.addSawScores();
		Game game = player.getGame();

		return game.getNumberOfPlayersLeftToSeeScores();
	}

//
	@Override
	public int roundsLeft(String gameCode) {
		// TODO Auto-generated method stub

		Game game = gamesRepository.getGame(gameCode);
		return game.roundsLeft();
	}

//	
	@Override
	public int sessionsLeft(String gameCode) {
		// TODO Auto-generated method stub

		Game game = gamesRepository.getGame(gameCode);
		return game.sessionsLeft();
	}

//
	@Override
	public PlayerPersonalInfo getWinner(String gameCode) {
		// TODO Auto-generated method stub

		Game game = gamesRepository.getGame(gameCode);
		return game.getWinner();
	}

//	
	@Override
	public int addPlayerContinueChoice(long playerId, boolean decision) {
		// TODO Auto-generated method stub

		Player player = playersRepository.getPlayer(playerId);
		player.addContinueDecision(decision);
		Game game = player.getGame();

		return game.getNumberOfPlayersLeftToDecideContinue();
	}

//
	@Override
	public synchronized boolean isAnotherGame(String gameCode) {
		// TODO Auto-generated method stub

		Game game = gamesRepository.getGame(gameCode);
		if (game == null) {
			return false;
		}
		
		boolean isAnotherGame = game.isAnotherGame();
		if (!isAnotherGame) {
			gamesRepository.removeGame(gameCode);
		}
		
		return isAnotherGame;
	}

}
