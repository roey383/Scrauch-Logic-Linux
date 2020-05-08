package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestApplicationByPlayers extends Thread{

	public static final int numOfPlayers = 4;
	public static final int numOfSessions = 2;
	public static final int ID_LENGTH = 9;
	public static final int IMAGE_WIDTH = 100;
	public static final int IMAGE_HEIGHT = 100;
	public static final int CHANCE_TO_CONTINUE = 70;
	private static final int NUMBER_OF_GROUPS = 10;
	private ScrouchLogicServiceAPI app;

	public TestApplicationByPlayers(ScrouchLogicServiceAPI app) {
		// TODO Auto-generated constructor stub
		this.app = app;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			ScrouchLogicServiceAPI app = new ScrouchGameLogicApp();

			List<TestApplicationByPlayers> groups = new ArrayList<TestApplicationByPlayers>();

			for (int i = 0; i < NUMBER_OF_GROUPS; i++) {
				TestApplicationByPlayers group = new TestApplicationByPlayers(app);
				groups.add(group);
			}
			
			for (TestApplicationByPlayers group : groups) {
				group.start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		DoubleBarrier barrier = new DoubleBarrier(numOfPlayers);
		StringBuilder gameCode = new StringBuilder();
		AtomicInteger numOfPlayers = new AtomicInteger(0);
		AtomicInteger numOfSessions = new AtomicInteger(0);

		PlayerInitiator playerInitiator = new PlayerInitiator(app, barrier, gameCode, numOfPlayers, numOfSessions);
		playerInitiator.setName("player 1");
		List<TestPlayer> joiners = new ArrayList<TestPlayer>();

		playerInitiator.start();
		ScrouchGameLogicApp.logger.info("Player initiator started: " + playerInitiator.getName());

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < numOfPlayers.get() - 1; i++) {
			TestPlayer joiner = new TestPlayer(app, barrier, gameCode, numOfPlayers, numOfSessions);
			joiner.setName("player " + (i + 2));
			joiners.add(joiner);
		}

		for (TestPlayer joiner : joiners) {
			joiner.start();
			ScrouchGameLogicApp.logger.info("joiner started: " + joiner.getName());
		}

	}

	
	



}
