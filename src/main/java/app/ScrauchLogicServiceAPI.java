package app;

import java.awt.Image;
import java.util.List;
import java.util.Map;

import logic.DrawingTrueSentencePair;
import logic.PlayerPersonalInfo;
import logic.Result;

public interface ScrauchLogicServiceAPI {
	
    /**
     * initialize a new game with giving params
     *
     * @param numOfPlayers - the number of players will participate in the game
     * @param numOfSessions - the number of sessions in the Game
     * @return String Game code. Unique for each game
     */
	public String newGame(int numOfPLayers, int numOfSessions); 
	
    /**
     * adding a player to an exiting game
     *
     * @param playerId - player id
     * @param gameCode - game code
     * @return int number of players left to join (of the current state of the game, 
     * 			not how many players left when this player joined
     */
	public int joinPlayerToGame(long playerId, String gameCode);
	
	/*
	 * 
	 */
	public int registerPlayerInformation(long playerId, String playerName, Image selfPortrait);
	
	/*
	 * 
	 */
	public List<PlayerPersonalInfo> getAllPlayersInformation(String gameCode);
	
	/*
	 * 
	 */
	public String getNextTrueSentence(String gameCode);
	
	/*
	 * 
	 */
	public int addPlayerDrawing(long playerId, Image drawing, String trueSentence);
	
	/*
	 * 
	 */
	public DrawingTrueSentencePair getCurrentDrawingSentencePlayer(String gameCode);
	
	/*
	 * 
	 */
	public int addPlayerFalseDiscriptionToCurrentDrawing(long playerId, String falseSentence);
	
	/*
	 * 
	 */
	public List<String> getAllSentencesToCurrentDrawing(String gameCode);
	
	/*
	 * 
	 */
	public List<String> getAllSentencesToCurrentDrawingExceptFalser(long playerId);
	
	/*
	 * 
	 */
	public String getPlayerCurrentFalse(long playerId);
	
	/*
	 * 
	 */
	public int addPlayerGuessToCurrentDrawing(long playerId, String guessSentence);
	
	/*
	 * the true is the last
	 * 
	 */
	public List<Result> getCurrentDrawingResults(String gameCode);
	
	/*
	 * 
	 */
	public Map<PlayerPersonalInfo, Integer> getLastRoundScoreBoard(String gameCode);
	
	/*
	 * 
	 */
	public Map<PlayerPersonalInfo, Integer> getTotalScoreBoard(String gameCode);
	
	/*
	 * 
	 */
	public int addPlayerSawScores(long playerId);
	
	/*
	 * 
	 */
	public int roundsLeft(String gameCode);
	
	/*
	 * 
	 */
	public int sessionsLeft(String gameCode);

	/*
	 * 
	 */
	public PlayerPersonalInfo getWinner(String gameCode);
	
	/*
	 * 
	 */
	public int addPlayerContinueChoice(long playerId, boolean decision);
	
	/*
	 * 
	 */
	public boolean isAnotherGame(String gameCode);

}
