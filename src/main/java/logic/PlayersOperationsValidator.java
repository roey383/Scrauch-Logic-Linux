package logic;

import java.util.concurrent.atomic.AtomicInteger;

public class PlayersOperationsValidator {
	
	private AtomicInteger numOfLoggedInPlayers;
	private AtomicInteger numOfPlayersRegisteredPersonalInfo;
	private AtomicInteger NumOfPlayersAlreadyDraw;
	private AtomicInteger NumOfPlayersAlreadyFalseCurrentDrawing;
	private AtomicInteger NumOfPlayersAlreadyGuessCurrentDiscription;
	private AtomicInteger NumberOfPlayersLeftToDecideContinue;
	
	public PlayersOperationsValidator() {
		super();
		numOfLoggedInPlayers = new AtomicInteger(0);
		numOfPlayersRegisteredPersonalInfo = new AtomicInteger(0);
		NumOfPlayersAlreadyDraw = new AtomicInteger(0);
		NumOfPlayersAlreadyFalseCurrentDrawing = new AtomicInteger(0);
		NumOfPlayersAlreadyGuessCurrentDiscription = new AtomicInteger(0);
		NumberOfPlayersLeftToDecideContinue = new AtomicInteger(0);
	}

	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		this.addPlayer(player);
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

	public int NumOfPlayersAlreadyFalseCurrentDrawing() {
		// TODO Auto-generated method stub
		return NumOfPlayersAlreadyFalseCurrentDrawing.intValue();
	}
	
	public void incrementNumOfPlayersAlreadyGuessCurrentDiscription() {
		// TODO Auto-generated method stub
		NumOfPlayersAlreadyGuessCurrentDiscription.addAndGet(1);
	}

	public int getNumOfPlayersAlreadyGuessCurrentDiscription() {
		// TODO Auto-generated method stub
		return NumOfPlayersAlreadyGuessCurrentDiscription.intValue();
	}
	
	public void incrementNumberOfPlayersLeftToDecideContinue() {
		// TODO Auto-generated method stub
		NumberOfPlayersLeftToDecideContinue.addAndGet(1);
	}

	public int getNumberOfPlayersLeftToDecideContinue() {
		// TODO Auto-generated method stub
		return NumberOfPlayersLeftToDecideContinue.intValue();
	}

}
