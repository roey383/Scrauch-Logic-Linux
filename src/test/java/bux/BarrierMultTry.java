package bux;

import java.util.ArrayList;
import java.util.List;

public class BarrierMultTry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int numberOfThreads = 3; // or any number you'd like
		BarrierTry barrier = new BarrierTry(3);

		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < numberOfThreads; i++) {
			threads.add(new Thread(new CoordinatedWorkRunner(barrier)));
		}

		for (Thread thread : threads) {
			thread.start();
		}

	}

	public static class BarrierTry {
		private final int numberOfWorkers;
		private int counter1 = 0;
		private int counter2 = 0;
		private boolean oddGate = true;

		public BarrierTry(int numberOfWorkers) {
			this.numberOfWorkers = numberOfWorkers;
		}

		public synchronized void barrier() {
			if (oddGate) {
				counter1++;
				while (counter1 < numberOfWorkers) {
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
				}
			} else {
				counter2++;
				while (counter2 < numberOfWorkers) {
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
				}
			}
		}

	}

	public static class CoordinatedWorkRunner implements Runnable {
		private BarrierTry barrier;

		public CoordinatedWorkRunner(BarrierTry barrier2) {
			this.barrier = barrier2;
		}

		@Override
		public void run() {
			try {
				task();
			} catch (InterruptedException e) {
			}
		}

		private void task() throws InterruptedException {
			// Performing Part 1

			for (int i = 0; i < 8; i++) {
				System.out.println(Thread.currentThread().getName() + " part " + i + " of the work is finished");

				barrier.barrier();

			}
			System.out.println(Thread.currentThread().getName() + " geting out...");

		}
	}

}
