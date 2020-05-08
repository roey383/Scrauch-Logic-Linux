package logic;

import java.awt.Image;

public class DrawingTrueSentencePair {

	private final long playerId;
	private Image drawing;
	private String trueSentence;

	public DrawingTrueSentencePair(long id, Image drawing, String trueSentence) {
		// TODO Auto-generated constructor stub
		this.playerId = id;
		this.drawing = drawing;
		this.trueSentence = trueSentence;
	}

	public long getPlayerId() {
		return playerId;
	}

	public Image getDrawing() {
		return drawing;
	}

	public String getTrueSentence() {
		return trueSentence;
	}

	@Override
	public String toString() {
		return "DrawingTrueSentencePair [playerId=" + playerId + ", drawing=" + drawing.hashCode()%1000 + ", trueSentence="
				+ trueSentence + "]";
	}

	
	
}
