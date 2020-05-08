package logic;

public class SentencesInventoryRetriver {
	
	private SentencesInventory sentencesInventory;
	private int offset;

	public SentencesInventoryRetriver(SentencesInventory sentencesInventory) {
		// TODO Auto-generated constructor stub
		this.sentencesInventory = sentencesInventory;
		this.offset = 0;
	}

	public synchronized String readNext() {
		// TODO Auto-generated method stub
		
		if (offset >= sentencesInventory.size()) {
			offset = 0;
		}
		return sentencesInventory.get(offset++);
		
	}

}
