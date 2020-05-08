package app;

import java.awt.Image;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import logic.DrawingTrueSentencePair;
import logic.PlayerPersonalInfo;
import logic.Result;

public class TestPlayer extends Thread {

	protected StringBuilder gameCode;
	protected long playerId;
	protected PlayerPersonalInfo info;
	protected Image drawing;
	protected String trueSentence;
	protected String currentFalseSentence;
	protected String currentGuessSentence;
	protected DrawingTrueSentencePair drawingSentencePair;
	protected boolean continueDecision;
	protected List<Result> roundResults;
	protected Map<PlayerPersonalInfo, Integer> scoreBoard;
	protected DoubleBarrier barrier;
	protected ScrouchLogicServiceAPI app;
	protected DrawingTrueSentencePair currentDrawingSentencePair;
	protected boolean anotherGame;
	protected AtomicInteger numOfSessions;
	protected AtomicInteger numOfPlayers;

	public TestPlayer(ScrouchLogicServiceAPI app, DoubleBarrier barrier, StringBuilder gameCode,
			AtomicInteger numOfPlayers, AtomicInteger numOfSessions) {
		// TODO Auto-generated constructor stub
		this.app = app;
		this.barrier = barrier;
		this.gameCode = gameCode;
		this.numOfPlayers = numOfPlayers;
		this.numOfSessions = numOfSessions;
		this.anotherGame = true;
		this.numOfSessions.set(TestApplicationByPlayers.numOfSessions);
		this.numOfPlayers.set(TestApplicationByPlayers.numOfPlayers);
		generatePlayerInputs();
	}

	@Override
	public void run() {

		int totalSessions = 0;
		joiningGameCommands();

		while (anotherGame) {

			anotherGame = false;

			for (int i = 0; i < this.numOfSessions.get(); i++) {

				totalSessions++;
				ScrouchGameLogicApp.logger.info(
						"game: " + gameCode.toString() + "- " + Thread.currentThread().getName() + " in session " + i);

				sessionCommands();

				for (int j = 0; j < this.numOfPlayers.get(); j++) {

					ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- "
							+ Thread.currentThread().getName() + " in round " + j);

					roundCommands();

					ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
							+ " rounds left: " + app.roundsLeft(gameCode.toString()) +"/" + numOfPlayers.get() + " (j=" + j + ")");
					ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
							+ " sessions left: " + app.sessionsLeft(gameCode.toString()) +"/" + numOfSessions.get() + " (i=" + i + ")");
				}
			}

			ScrouchGameLogicApp.logger.warn("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
					+ " cheers for the winner: " + app.getWinner(this.gameCode.toString()).getName());

			anotherGame = decidingContinueGameCommand();
		}

		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " is quiting after " + totalSessions + " sessions ...");

	}

	private StringBuilder joiningGameCommands() {
		app.joinPlayerToGame(playerId, gameCode.toString());
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " with id: " + playerId + " joined game " + gameCode.toString());

		barrier.barrier();

		app.registerPlayerInformation(playerId, this.info.getName(), this.info.getProfil());
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " with name: " + this.info.getName() + " with profil: " + this.info.getProfil().hashCode() % 1000
				+ " registered himself to " + gameCode.toString());

		barrier.barrier();

		StringBuilder message = new StringBuilder(app.getAllPlayersInformation(gameCode.toString()).toString());
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " gets players info\n" + message.toString());

		barrier.barrier();
		return message;
	}

	private void sessionCommands() {

		this.trueSentence = app.getNextTrueSentence(gameCode.toString());
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " got sentence: " + this.trueSentence);

		barrier.barrier();

		Image drawing = InfoGenerator.generateImage();
		app.addPlayerDrawing(playerId, drawing, this.trueSentence);
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " added drawing " + drawing.hashCode() % 1000 + " to sentence " + this.trueSentence);

		barrier.barrier();
	}

	private void roundCommands() {

		this.currentDrawingSentencePair = app.getCurrentDrawingSentencePlayer(gameCode.toString());
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " got drawing " + this.currentDrawingSentencePair.getDrawing().hashCode() % 1000);

		if (this.currentDrawingSentencePair.getPlayerId() != this.playerId) {

			String falseSentence = InfoGenerator.generateFalseSentence();
			app.addPlayerFalseDiscriptionToCurrentDrawing(playerId, falseSentence);
			ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
					+ " added false sentence " + falseSentence);
		}

		barrier.barrier();

		List<String> allSentences = app.getAllSentencesToCurrentDrawingExceptFalser(playerId);
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " got all sentences to drawing: " + allSentences.toString());

		if (this.currentDrawingSentencePair.getPlayerId() != this.playerId) {
			String guess = InfoGenerator.guessSentence(allSentences);
			app.addPlayerGuessToCurrentDrawing(playerId, guess);
			ScrouchGameLogicApp.logger.info(
					"game: " + gameCode.toString() + "- " + Thread.currentThread().getName() + " picked " + guess);
		}

		barrier.barrier();

		StringBuilder message = new StringBuilder();
		message.append(app.getCurrentDrawingResults(gameCode.toString()));
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " got results: " + message.toString());

		barrier.barrier();

		Map<PlayerPersonalInfo, Integer> scores = app.getLastRoundScoreBoard(gameCode.toString());
		message.delete(0, message.length());
		message.append(InfoGenerator.mapToString(scores));
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " got scores to last round: " + message.toString());

		barrier.barrier();

		Map<PlayerPersonalInfo, Integer> totalScores = app.getTotalScoreBoard(gameCode.toString());
		message.delete(0, message.length());
		message.append(InfoGenerator.mapToString(totalScores));
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " got total scores: " + message.toString());

		barrier.barrier();

		app.addPlayerSawScores(playerId);
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " saw scores table");

		barrier.barrier();
		
	}

	private boolean decidingContinueGameCommand() {

		boolean decision = InfoGenerator.generateDecision();
		app.addPlayerContinueChoice(playerId, decision);
		ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
				+ " notify decided to " + (decision ? "continue" : "quit"));

		numOfPlayers.set(0);

		barrier.barrier();

		if (decision) {
			if (app.isAnotherGame(gameCode.toString())) {
				ScrouchGameLogicApp.logger.info("game: " + gameCode.toString() + "- " + Thread.currentThread().getName()
						+ " got answer is another game: true");
				numOfPlayers.addAndGet(1);
				numOfSessions.set(1);
				barrier.setNumberOfWorkers(app.getAllPlayersInformation(gameCode.toString()).size());
				return true;
			}

		}

		return false;
	}

	private void generatePlayerInputs() {
		// TODO Auto-generated method stub

		this.playerId = InfoGenerator.generateId();
		this.info = new PlayerPersonalInfo(playerId, InfoGenerator.generateName(), InfoGenerator.generateImage());

	}

}
