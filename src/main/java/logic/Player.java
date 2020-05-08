package logic;

import java.awt.Image;

public class Player {
	
	private final long id;
	private Game game;
	private PlayerPersonalInfo info;
	private Image drawing;
	private String trueSentence;
	private String currentFalseSentence;
	private String currentGuessSentence;
	private boolean continueDecision;
	private boolean sawScores;
	
	public Player(long id, Game game) {
		super();
		this.id = id;
		this.game = game;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void addInfo(String playerName, Image selfPortrait) {
		// TODO Auto-generated method stub
		
		this.info = new PlayerPersonalInfo(id, playerName, selfPortrait);
		game.players.incrementRegisteredPlayersInfo();
		
	}

	public Game getGame() {
		// TODO Auto-generated method stub
		return game;
	}

	public PlayerPersonalInfo getInfo() {
		// TODO Auto-generated method stub
		return info;
	}

	public void addDrawing(Image drawing, String trueSentence) {
		// TODO Auto-generated method stub
		
		this.drawing = drawing;
		this.trueSentence = trueSentence;
		game.players.incrementNumOfPlayersAlreadyDraw();
	}

	public void addFalseSentenceToCurrentDrawing(String falseSentence) {
		// TODO Auto-generated method stub
		
		this.currentFalseSentence = falseSentence;
		game.players.incrementNumOfPlayersAlreadyFalseCurrentDrawing();
		
	}

	public Image getDrawing() {
		// TODO Auto-generated method stub
		return drawing;
	}

	public String getTrueSentene() {
		// TODO Auto-generated method stub
		return trueSentence;
	}

	public String getCurrentFalseSentence() {
		// TODO Auto-generated method stub
		return currentFalseSentence;
	}

	public void addGuessSentenceToCurrentDrawing(String guessSentence) {
		// TODO Auto-generated method stub
		
		this.currentGuessSentence = guessSentence;
		game.players.incrementNumOfPlayersAlreadyGuessCurrentDrawing();;
		
	}

	public String getCurrentGuessSentence() {
		// TODO Auto-generated method stub
		return currentGuessSentence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		return true;
	}

	public void addContinueDecision(boolean decision) {
		// TODO Auto-generated method stub
		
		this.continueDecision = decision;
		game.players.incrementNumberOfPlayersLeftToDecideContinue();
		
	}

	public boolean getDicisionContinue() {
		// TODO Auto-generated method stub
		return this.continueDecision;
	}

	public void addSawScores() {
		// TODO Auto-generated method stub
		this.sawScores = true;
		game.players.incrementNumberOfPlayersLeftToSawScores();
		
	}

	public void resetFieldsRound() {
		// TODO Auto-generated method stub
		this.currentFalseSentence = null;
		this.currentGuessSentence = null;
		this.sawScores = false;
		
	}

	
	
}
