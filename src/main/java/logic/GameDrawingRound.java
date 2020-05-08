package logic;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameDrawingRound {

	private Image drawing;
	private int numOfPlayers;
	private String trueSentence;
	private Player painter;
	private List<Player> observers;
	private List<String> falseSentences;
	private List<String> guessings;
	private List<Result> roundResults;
	private List<String> allSentences;
	private Map<PlayerPersonalInfo, Integer> scoreBoard;
	private boolean resultsGenerated;
	private boolean scoreBoardGenerated;
	private boolean allSentencesGenerated;
	
	public GameDrawingRound(Image drawing, int numOfPlayers, String trueSentence, Player painter) {
		super();
		this.drawing = drawing;
		this.numOfPlayers = numOfPlayers;
		this.trueSentence = trueSentence;
		this.painter = painter;
		this.observers = new ArrayList<Player>();
		this.falseSentences = new ArrayList<String>();
		this.guessings = new ArrayList<String>();
		allocateListIndexes(this.guessings);
		this.roundResults = new ArrayList<Result>();
		this.scoreBoard = new ConcurrentHashMap<PlayerPersonalInfo, Integer>();
		this.resultsGenerated = false;
		this.scoreBoardGenerated = false;
		this.allSentences = new ArrayList<String>();
		this.allSentencesGenerated = false;
		
	}

	private void allocateListIndexes(List<String> list) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < numOfPlayers-1; i++) {
			list.add("");
		}
	}

	public DrawingTrueSentencePair generatePair() {
		// TODO Auto-generated method stub
		
		return new DrawingTrueSentencePair(painter.getId(), drawing, trueSentence);
	}

	public void addFalser(Player falser) {
		// TODO Auto-generated method stub
		
		observers.add(falser);
		falseSentences.add(falser.getCurrentFalseSentence());
		
	}


	public List<String> getAllSentencesToCurrentDrawing() {
		// TODO Auto-generated method stub
		
		generateAllSentences();

		return allSentences;
	}
	
	public List<String> getAllSentencesToCurrentDrawingExceptFalser(long playerId, String falseSentence) {
		// TODO Auto-generated method stub
		
		generateAllSentences();
		
		if (painter.getId() == playerId) {
			return this.allSentences;
		}
		
		List<String> allSentencesExceptFalser = new ArrayList<String>();
		
		for (String sentence : allSentences) {
			if (!sentence.equals(falseSentence)) {
				allSentencesExceptFalser.add(sentence);
			}
		}
		return allSentencesExceptFalser;
	}

	
	
	private synchronized void generateAllSentences() {
		// TODO Auto-generated method stub
		
		if(this.allSentencesGenerated) {
			return;
		}
		
//		this.allSentences = this.falseSentences.subList(0, falseSentences.size());
		for (String falseSentence : falseSentences) {
			this.allSentences.add(falseSentence);
		}
		allSentences.add(trueSentence);
		Collections.shuffle(allSentences);
		
		this.allSentencesGenerated = true;
		
	}

	public void addGuesser(Player guesser) {
		// TODO Auto-generated method stub
		
		int index = observers.indexOf(guesser); 
		this.guessings.set(index, guesser.getCurrentGuessSentence());
		
	}

	public List<Result> getResults() {
		// TODO Auto-generated method stub
		
		generateResults();
		
		return roundResults;
	}

	private synchronized void generateResults() {
		// TODO Auto-generated method stub
		
		if(resultsGenerated) {
			return;
		}
		
		for (int i = 0; i < numOfPlayers-1; i++) {
			
			Result result = new Result(observers.get(i).getInfo(), 
					falseSentences.get(i), 
					generateFalseChoosers(i), 
					false);
			
			roundResults.add(result);
		}
		
		roundResults.add(new Result(painter.getInfo(), 
					trueSentence, 
					generateTrueChoosers(), 
					true));
		
		resultsGenerated = true;
		
	}

	private List<PlayerPersonalInfo> generateTrueChoosers() {
		// TODO Auto-generated method stub		
		
		List<PlayerPersonalInfo> choosers = new ArrayList<PlayerPersonalInfo>();
		
		for (int j = 0; j < numOfPlayers-1; j++) {
			if (guessings.get(j).equals(trueSentence)) {
				choosers.add(observers.get(j).getInfo());
			}
		}
		
		return choosers;
	}

	private List<PlayerPersonalInfo> generateFalseChoosers(int index) {
		// TODO Auto-generated method stub
		
		List<PlayerPersonalInfo> choosers = new ArrayList<PlayerPersonalInfo>();
		
		for (int j = 0; j < numOfPlayers-1; j++) {
			if (guessings.get(j).equals(falseSentences.get(index))) {
				choosers.add(observers.get(j).getInfo());
			}
		}
		
		return choosers;
	}

	public Map<PlayerPersonalInfo, Integer> getScoreBoard() {
		// TODO Auto-generated method stub
		
		generateScoreBoard();
		
		return scoreBoard;
	}

	private synchronized void generateScoreBoard() {
		
		if (scoreBoardGenerated) {
			return;
		}
		
		for (Result result : roundResults) {
			scoreBoard.put(result.getPlayer(), computeScore(result));
		}
		
		for (PlayerPersonalInfo guesser : roundResults.get(numOfPlayers-1).getChoosers()) {
			scoreBoard.put(guesser, scoreBoard.get(guesser) + Result.POINTS_EARNED_FOR_HEAD);
		}
		
		scoreBoardGenerated = true;
	}

	private Integer computeScore(Result result) {
		// TODO Auto-generated method stub
		
		return result.getPointsCollectPerHead() * result.getChoosers().size();
	}

	public Player getPainter() {
		return painter;
	}

	
	
	
	
	

}
