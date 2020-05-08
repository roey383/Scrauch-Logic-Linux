package bux;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import app.ScrouchGameLogicApp;
import app.ScrouchLogicServiceAPI;

public class TestFrontEndServerHandlers {

	private static final int numOfPlayers = 4;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ScrouchLogicServiceAPI app = new ScrouchGameLogicApp();
			Barrier barrier = new Barrier(numOfPlayers);
			
			startOneGame(app, barrier);
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private static void startOneGame(ScrouchLogicServiceAPI app, Barrier barrier) {
		// TODO Auto-generated method stub
		
		List<PlayerHandler> playerHandlers = new ArrayList<PlayerHandler>(); 
		
		for (int i = 0; i < numOfPlayers; i++) {
			PlayerHandler playerHandler = new PlayerHandler(app, barrier);
			playerHandlers.add(playerHandler);
		}
		
		for (PlayerHandler playerHandler : playerHandlers) {
			playerHandler.start();
		}
		
		
		
	}
	
	public static class PlayerHandler extends Thread{

		public PlayerHandler(ScrouchLogicServiceAPI app, Barrier barrier) {
			// TODO Auto-generated constructor stub
			
			if (true) {
				
			}
		}
		
		
		
	}
	

	public static class Barrier {
	    private final int numberOfWorkers;
	    private Semaphore semaphore = new Semaphore(0);
	    private int counter = 0;
	    private Lock lock = new ReentrantLock();
	 
	    public Barrier(int numberOfWorkers) {
	        this.numberOfWorkers = numberOfWorkers;
	    }
	 
	    public void barrier() {
	        lock.lock();
	        boolean isLastWorker = false;
	        try {
	            counter++;
	 
	            if (counter == numberOfWorkers) {
	                isLastWorker = true;
	                counter = 0;
	            }
	        } finally {
	            lock.unlock();
	        }
	 
	        if (isLastWorker) {
	            semaphore.release(numberOfWorkers-1);
	        } else {
	            try {
	                semaphore.acquire();
	            } catch (InterruptedException e) {
	            }
	        }
	    }
	}

	
	
	

}
