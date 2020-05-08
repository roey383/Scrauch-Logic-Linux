package logic;

import java.util.List;

import config.Config;

public final class Result {
	
	public static final int POINTS_COLLECT_PER_HEAD_TRUE = 
			Integer.parseInt(Config.getProperty(Config.POINTS_COLLECT_PER_HEAD_TRUE));
	public static final int POINTS_COLLECT_PER_HEAD_FALSE = 
			Integer.parseInt(Config.getProperty(Config.POINTS_COLLECT_PER_HEAD_FALSE));
	public static final int POINTS_EARNED_FOR_HEAD = 
			Integer.parseInt(Config.getProperty(Config.POINTS_EARNED_FOR_HEAD));
	
	protected PlayerPersonalInfo player;
	private String sentence;
	protected List<PlayerPersonalInfo> choosers;
	protected int pointsCollectPerHead;
	private int pointsEarnedForHead;
	private boolean isTrueSentence;
	
	public Result(PlayerPersonalInfo player, String sentence, List<PlayerPersonalInfo> choosers,
			boolean isTrueSentence) {
		super();
		this.player = player;
		this.sentence = sentence;
		this.choosers = choosers;
		this.isTrueSentence = isTrueSentence;	
		
		this.pointsCollectPerHead = isTrueSentence ?  
				POINTS_COLLECT_PER_HEAD_TRUE:
				POINTS_COLLECT_PER_HEAD_FALSE;
		this.pointsEarnedForHead = POINTS_EARNED_FOR_HEAD;

	}

	public PlayerPersonalInfo getPlayer() {
		return player;
	}

	public void setPlayer(PlayerPersonalInfo player) {
		this.player = player;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public List<PlayerPersonalInfo> getChoosers() {
		return choosers;
	}

	public void setChoosers(List<PlayerPersonalInfo> choosers) {
		this.choosers = choosers;
	}

	public int getPointsCollectPerHead() {
		return pointsCollectPerHead;
	}

	public void setPointsCollectPerHead(int pointsCollectPerHead) {
		this.pointsCollectPerHead = pointsCollectPerHead;
	}

	public int getPointsEarnedForHead() {
		return pointsEarnedForHead;
	}

	public void setPointsEarnedForHead(int pointsEarnedForHead) {
		this.pointsEarnedForHead = pointsEarnedForHead;
	}

	public boolean isTrueSentence() {
		return isTrueSentence;
	}

	public void setTrueSentence(boolean isTrueSentence) {
		this.isTrueSentence = isTrueSentence;
	}

	@Override
	public String toString() {
		return "Result [player=" + player.getName() + ", sentence=" + sentence + ",\nchoosers=" + choosers
				+ ",\nisTrueSentence=" + isTrueSentence + "]\n";
	}
	
	


}
