package app;

import java.util.concurrent.atomic.AtomicInteger;

public class PlayerInitiator extends TestPlayer {

	public PlayerInitiator(ScrouchLogicServiceAPI app, DoubleBarrier barrier, StringBuilder gameCode, AtomicInteger numOfPlayers,
			AtomicInteger numOfSessions) {
		// TODO Auto-generated constructor stub
		super(app, barrier, gameCode, numOfPlayers, numOfSessions);
	}

	@Override
	public void run() {

		this.gameCode.append(app.newGame(TestApplicationByPlayers.numOfPlayers, 
				TestApplicationByPlayers.numOfSessions));

		ScrouchGameLogicApp.logger.info("initiator created a new game with code: " + gameCode.toString());
		
		super.run();

	}
}
