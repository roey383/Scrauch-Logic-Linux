package app;

import java.util.concurrent.atomic.AtomicInteger;

public class DoubleBarrier {
	private AtomicInteger numberOfWorkers;
	private int counter1 = 0;
	private int counter2 = 0;
	private boolean oddGate = true;

	public DoubleBarrier(int numberOfWorkers) {
		this.numberOfWorkers = new AtomicInteger(numberOfWorkers);
	}

	public synchronized void barrier() {
//		ScrouchGameLogicApp.logger.info(Thread.currentThread().getName() + " in barrier");
		if (oddGate) {
			counter1++;
			while (counter1 < numberOfWorkers.intValue()) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (oddGate) {
				counter2 = 0;
				oddGate = false;
				notifyAll();
//				ScrouchGameLogicApp.logger
//				.info(Thread.currentThread().getName() + " frees " + (numberOfWorkers.intValue() - 1) + " players");
			}
		} else {
			counter2++;
			while (counter2 < numberOfWorkers.intValue()) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!oddGate) {
				counter1 = 0;
				oddGate = true;
				notifyAll();
//				ScrouchGameLogicApp.logger
//				.info(Thread.currentThread().getName() + " frees " + (numberOfWorkers.intValue() - 1) + " players");
			}
		}
//		ScrouchGameLogicApp.logger.info(Thread.currentThread().getName() + " out of barrier");
	}

	public void setNumberOfWorkers(int numberOfWorkers) {
		this.numberOfWorkers.set(numberOfWorkers);
	}

	
	

}