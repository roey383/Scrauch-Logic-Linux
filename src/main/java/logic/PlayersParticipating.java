package logic;

import java.util.concurrent.atomic.AtomicInteger;

public class PlayersParticipating extends Players {

	private AtomicInteger numOfLoggedInPlayers;
	private AtomicInteger numOfPlayersRegisteredPersonalInfo;
	private AtomicInteger NumOfPlayersAlreadyDraw;
	private AtomicInteger NumOfPlayersAlreadyFalseCurrentDrawing;
	private AtomicInteger NumOfPlayersAlreadyGuessCurrentDrawing;
	private AtomicInteger NumOfPlayersAlreadyDecideContinue;
	private AtomicInteger NumOfPlayersAlreadySawScores;
	
	public PlayersParticipating() {
		super();
		numOfLoggedInPlayers = new AtomicInteger(0);
		numOfPlayersRegisteredPersonalInfo = new AtomicInteger(0);
		NumOfPlayersAlreadyDraw = new AtomicInteger(0);
		NumOfPlayersAlreadyFalseCurrentDrawing = new AtomicInteger(0);
		NumOfPlayersAlreadyGuessCurrentDrawing = new AtomicInteger(0);
		NumOfPlayersAlreadyDecideContinue = new AtomicInteger(0);
		NumOfPlayersAlreadySawScores = new AtomicInteger(0);
	}

	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		super.addPlayer(player);
		numOfLoggedInPlayers.addAndGet(1);
	}

	public int getNumOfLoggedInPlayers() {
		// TODO Auto-generated method stub
		return numOfLoggedInPlayers.intValue();
	}


	public void incrementRegisteredPlayersInfo() {
		// TODO Auto-generated method stub
		numOfPlayersRegisteredPersonalInfo.addAndGet(1);
	}

	public int getNumOfPlayersAlreadyRegisteredInfo() {
		// TODO Auto-generated method stub
		return numOfPlayersRegisteredPersonalInfo.intValue();
	}
	
	public void incrementNumOfPlayersAlreadyDraw() {
		// TODO Auto-generated method stub
		NumOfPlayersAlreadyDraw.addAndGet(1);
	}

	public int getNumOfPlayersAlreadyDraw() {
		// TODO Auto-generated method stub
		return NumOfPlayersAlreadyDraw.intValue();
	}
	
	public void incrementNumOfPlayersAlreadyFalseCurrentDrawing() {
		// TODO Auto-generated method stub
		NumOfPlayersAlreadyFalseCurrentDrawing.addAndGet(1);
	}

	public int getNumOfPlayersAlreadyFalseCurrentDrawing() {
		// TODO Auto-generated method stub
		return NumOfPlayersAlreadyFalseCurrentDrawing.intValue();
	}
	
	public void incrementNumOfPlayersAlreadyGuessCurrentDrawing() {
		// TODO Auto-generated method stub
		NumOfPlayersAlreadyGuessCurrentDrawing.addAndGet(1);
	}

	public int getNumOfPlayersAlreadyGuessCurrentDrawing() {
		// TODO Auto-generated method stub
		return NumOfPlayersAlreadyGuessCurrentDrawing.intValue();
	}
	
	public void incrementNumberOfPlayersLeftToDecideContinue() {
		// TODO Auto-generated method stub
		NumOfPlayersAlreadyDecideContinue.addAndGet(1);
	}

	public int getNumberOfPlayersAlreadyDecideContinue() {
		// TODO Auto-generated method stub
		return NumOfPlayersAlreadyDecideContinue.intValue();
	}

	public void resetCounters() {
		// TODO Auto-generated method stub
		NumOfPlayersAlreadyDraw.set(0);
		NumOfPlayersAlreadyFalseCurrentDrawing.set(0);
		NumOfPlayersAlreadyGuessCurrentDrawing.set(0);
		NumOfPlayersAlreadyDecideContinue.set(0);
		NumOfPlayersAlreadySawScores.set(0);
		
	}

	public void incrementNumberOfPlayersLeftToSawScores() {
		// TODO Auto-generated method stub
		NumOfPlayersAlreadySawScores.addAndGet(1);
		
	}

	public int getNumberOfPlayersAlreadySawScores() {
		// TODO Auto-generated method stub
		return NumOfPlayersAlreadySawScores.intValue();
	}

	public void resetFieldsRound() {
		// TODO Auto-generated method stub
		for (Player player : this) {
			player.resetFieldsRound();
		}
	}



}
