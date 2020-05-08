package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import config.Config;

public class Game {

	private final String gameCode;

	PlayersParticipating players;

	private int numOfSessions;
	private int numOfPlayers;
	private int roundNumber;
	private int sessionNumber;
	private Map<PlayerPersonalInfo, Integer> scoreBoard;
	private SentencesInventoryRetriver sentencesRetriever;
	private List<GameDrawingRound> gameDrawingRounds;
	private boolean isDrawingRoundsGenerated;
	private boolean isFalseSentencesAddedToCurrentRoud;
	private boolean isGuessesAddedToCurrentRoud;
	private boolean isScoreBoardUpdated;
	private boolean isAnotherGame;
	private boolean isContinueDecisionGenerated;
	private boolean isInitScoreBoard;
	private boolean isWinnerGenerated;

	private PlayerPersonalInfo winner;

	public Game(String code, int numOfPLayers, int numOfSessions, SentencesInventoryRetriver sentencesRetriever) {
		// TODO Auto-generated constructor stub
		super();
		this.numOfSessions = numOfSessions;
		this.numOfPlayers = numOfPLayers;
		this.gameCode = code;
		this.scoreBoard = new ConcurrentHashMap<PlayerPersonalInfo, Integer>();
		this.players = new PlayersParticipating();
		this.sentencesRetriever = sentencesRetriever;
		this.gameDrawingRounds = new ArrayList<GameDrawingRound>();
		this.isDrawingRoundsGenerated = false;
		this.isFalseSentencesAddedToCurrentRoud = false;
		this.isGuessesAddedToCurrentRoud = false;
		this.roundNumber = 0;
		this.isScoreBoardUpdated = false;
		this.sessionNumber = 0;
		this.isAnotherGame = false;
		this.isContinueDecisionGenerated = false;
		this.isInitScoreBoard = false;
		this.isWinnerGenerated = false;

	}

	private synchronized void initPlayersToScoreBoard() {
		// TODO Auto-generated method stub

		if (isInitScoreBoard) {
			return;
		}

		scoreBoard = new ConcurrentHashMap<PlayerPersonalInfo, Integer>();

		for (Player player : players) {
			PlayerPersonalInfo playerInfo = player.getInfo();
			scoreBoard.put(playerInfo, 0);
		}

		isInitScoreBoard = true;

	}

	public void addPlayer(long playerId) {
		// TODO Auto-generated method stub

		Player player = new Player(playerId, this);
		players.addPlayer(player);

	}

	public Player getPlayer(long playerId) {
		// TODO Auto-generated method stub

		return players.getPlayer(playerId);
	}

	public int getNumOfPlayers() {
		// TODO Auto-generated method stub
		return numOfPlayers;
	}

	public int getNumOfLoggedInPlayers() {
		// TODO Auto-generated method stub
		return players.getNumOfLoggedInPlayers();
	}

	public int getNumOfPlayersAlreadyRegisteredInfo() {
		// TODO Auto-generated method stub
		return players.getNumOfPlayersAlreadyRegisteredInfo();
	}

	public List<PlayerPersonalInfo> getPlayersInfo() {
		// TODO Auto-generated method stub

		initPlayersToScoreBoard();

		List<PlayerPersonalInfo> playersInfo = new ArrayList<PlayerPersonalInfo>();

		for (Player player : players) {
			playersInfo.add(player.getInfo());
		}
		return playersInfo;
	}

	public String getNextTrueSentence() {
		// TODO Auto-generated method stub
		return sentencesRetriever.readNext();
	}

	public int getNumOfPlayersAlreadyDraw() {
		// TODO Auto-generated method stub
		return players.getNumOfPlayersAlreadyDraw();
	}

	public DrawingTrueSentencePair getCurrentDrawingSentencePlayer() {
		// TODO Auto-generated method stub

		return gameDrawingRounds.get(roundNumber).generatePair();
	}

	public synchronized void genereateDrawingRounds() {
		// TODO Auto-generated method stub

		if (isDrawingRoundsGenerated) {
			return;
		}

		for (Player painter : players) {

			GameDrawingRound round = new GameDrawingRound(painter.getDrawing(), numOfPlayers, painter.getTrueSentene(),
					painter);
			gameDrawingRounds.add(round);
		}

		Collections.shuffle(gameDrawingRounds);

		this.isContinueDecisionGenerated = false;
		this.isAnotherGame = false;
		this.isScoreBoardUpdated = false;

		isDrawingRoundsGenerated = true;

	}

	public int getNumberOfPlayersLeftToDraw() {
		// TODO Auto-generated method stub
		int numberOfPlayersLeftToDraw = getNumOfPlayers() - getNumOfPlayersAlreadyDraw();

		if (numberOfPlayersLeftToDraw == 0) {

			genereateDrawingRounds();
		}

		return numberOfPlayersLeftToDraw;
	}

	public int getNumberOfPlayersLeftToWriteFalseSentence() {
		// TODO Auto-generated method stub
		int numberOfPlayersLeftToWriteFalseSentence = getNumOfPlayers() - 1
				- getNumOfPlayersAlreadyFalseCurrentDrawing();

		if (numberOfPlayersLeftToWriteFalseSentence == 0) {

			addFalseSentencesToCurrentRound();
		}

		return numberOfPlayersLeftToWriteFalseSentence;
	}

	private synchronized void addFalseSentencesToCurrentRound() {
		// TODO Auto-generated method stub

		if (isFalseSentencesAddedToCurrentRoud) {
			return;
		}

		for (Player falser : players) {

			long falserId = falser.getId();
			long painterId = gameDrawingRounds.get(roundNumber).getPainter().getId();
			if (falserId == painterId) {
				continue;
			}
			gameDrawingRounds.get(roundNumber).addFalser(falser);

		}

		isFalseSentencesAddedToCurrentRoud = true;

	}

	private int getNumOfPlayersAlreadyFalseCurrentDrawing() {
		// TODO Auto-generated method stub
		return players.getNumOfPlayersAlreadyFalseCurrentDrawing();
	}

	public int getNumberOfPlayersLeftToWriteGuessSentence() {
		// TODO Auto-generated method stub

		int numberOfPlayersLeftToWriteGuessSentence = getNumOfPlayers() - 1
				- getNumOfPlayersAlreadyGuessCurrentDrawing();

		if (numberOfPlayersLeftToWriteGuessSentence == 0) {

			addGuessesToCurrentRound();
		}

		return numberOfPlayersLeftToWriteGuessSentence;
	}

	public List<String> getAllSentencesToCurrentDrawing() {
		// TODO Auto-generated method stub

		return gameDrawingRounds.get(roundNumber).getAllSentencesToCurrentDrawing();
	}
	
	public List<String> getAllSentencesToCurrentDrawingExceptFalser(long playerId) {
		// TODO Auto-generated method stub
		
		String falseSentence = players.getPlayer(playerId).getCurrentFalseSentence();
		return gameDrawingRounds.get(roundNumber).getAllSentencesToCurrentDrawingExceptFalser(playerId, falseSentence);
	}

	private synchronized void addGuessesToCurrentRound() {
		// TODO Auto-generated method stub

		if (isGuessesAddedToCurrentRoud) {
			return;
		}

		for (Player guesser : players) {

			long guesserId = guesser.getId();
			long painterId = gameDrawingRounds.get(roundNumber).getPainter().getId();
			if (guesserId == painterId) {
				continue;
			}
			gameDrawingRounds.get(roundNumber).addGuesser(guesser);

		}

		isGuessesAddedToCurrentRoud = true;

	}

	private int getNumOfPlayersAlreadyGuessCurrentDrawing() {
		// TODO Auto-generated method stub
		return players.getNumOfPlayersAlreadyGuessCurrentDrawing();
	}

	public List<Result> getCurrentDrawingResults() {
		// TODO Auto-generated method stub

		return gameDrawingRounds.get(roundNumber).getResults();
	}

	public Map<PlayerPersonalInfo, Integer> getLastRoundScoreBoard() {
		// TODO Auto-generated method stub
//		this.isScoreBoardUpdated = false;
		return gameDrawingRounds.get(roundNumber).getScoreBoard();
	}

	public Map<PlayerPersonalInfo, Integer> getTotalScoreBoard() {
		// TODO Auto-generated method stub

		updateScoreBoard();

		return scoreBoard;
	}

	private synchronized void updateScoreBoard() {
		// TODO Auto-generated method stub

		if (isScoreBoardUpdated == true) {
			return;
		}

		Map<PlayerPersonalInfo, Integer> scoreBoardLastRound = gameDrawingRounds.get(roundNumber).getScoreBoard();
		for (PlayerPersonalInfo player : scoreBoardLastRound.keySet()) {
			scoreBoard.put(player, scoreBoard.get(player) + scoreBoardLastRound.get(player));
		}

		isScoreBoardUpdated = true;

	}

	public int getNumberOfPlayersLeftToSeeScores() {
		// TODO Auto-generated method stub
		int numberOfPlayersLeftToSeeScores = getNumOfPlayers() - players.getNumberOfPlayersAlreadySawScores();
		
		if (numberOfPlayersLeftToSeeScores == 0) {

			initRoundAndSesion();
			
		}

		return numberOfPlayersLeftToSeeScores;
	}

	private synchronized void initRoundAndSesion() {
		// TODO Auto-generated method stub
		
		initNewRound();
		if (roundNumber == numOfPlayers) {
			initNewSession();
		}
		
	}

	private void initNewRound() {
		// TODO Auto-generated method stub
		this.roundNumber++;
		this.isFalseSentencesAddedToCurrentRoud = false;
		this.isGuessesAddedToCurrentRoud = false;
		this.isScoreBoardUpdated = false;
		this.players.resetCounters();
		this.players.resetFieldsRound();
	}

	private void initNewSession() {
		// TODO Auto-generated method stub

		this.sessionNumber++;
		this.roundNumber = 0;
		this.isDrawingRoundsGenerated = false;
		this.gameDrawingRounds = new ArrayList<GameDrawingRound>();

	}

	public synchronized int roundsLeft() {
		// TODO Auto-generated method stub
		return (numOfPlayers - roundNumber) % numOfPlayers;
	}

	// sessions left not include current playing session
	public synchronized int sessionsLeft() {
		// TODO Auto-generated method stub
		return Math.max(numOfSessions - sessionNumber, 0);
	}

	public PlayerPersonalInfo getWinner() {
		// TODO Auto-generated method stub

		generateWinner();

		return winner;
	}

	private synchronized void generateWinner() {
		// TODO Auto-generated method stub

		if (isWinnerGenerated) {
			return;
		}

		Map.Entry<PlayerPersonalInfo, Integer> maxEntry = null;

		for (Map.Entry<PlayerPersonalInfo, Integer> entry : scoreBoard.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}

		this.winner = maxEntry.getKey();
		this.isInitScoreBoard = false;
		initPlayersToScoreBoard();

		isWinnerGenerated = true;

	}

	public synchronized int getNumberOfPlayersLeftToDecideContinue() {
		// TODO Auto-generated method stub
		int numberOfPlayersLeftToDecideContinue = getNumOfPlayers() - players.getNumberOfPlayersAlreadyDecideContinue();

		return numberOfPlayersLeftToDecideContinue;
	}

	public boolean isAnotherGame() {
		// TODO Auto-generated method stub

		calculateContinueDecision();

		return isAnotherGame;
	}

	private synchronized void calculateContinueDecision() {

		if (isContinueDecisionGenerated == true) {
			return;
		}

		int count = 0;

		Set<Entry<Long, Player>> setOfEntries = players.getEntrySet();
		Iterator<Entry<Long, Player>> iterator = setOfEntries.iterator();
		while (iterator.hasNext()) {
			Entry<Long, Player> entry = iterator.next();
			Player player = entry.getValue();
			count += player.getDicisionContinue() ? 1 : 0;
			if (!player.getDicisionContinue()) {
				iterator.remove();
				this.scoreBoard.remove(player.getInfo());
			}
		}

		isAnotherGame = count >= Integer.parseInt(Config.getProperty(Config.MIN_NUM_OF_PLAYERS)) ? true : false;
		numOfPlayers = count;
		numOfSessions = 1;
		
		isContinueDecisionGenerated = true;
		isWinnerGenerated = false;
	}

	public String getGameCode() {
		return gameCode;
	}



	
}