package org.chs.test.model;

import org.chs.test.constants.GameStatus;

public class Game {

	private Board board;
	private GameStatus gameStatus;
	private int round = 0;
	
	public Game(Board board) {
		this.board = board;
		this.gameStatus = GameStatus.START;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * @return the gameStatus
	 */
	public GameStatus getGameStatus() {
		return gameStatus;
	}

	/**
	 * @param gameStatus the gameStatus to set
	 */
	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	/**
	 * @return the round
	 */
	public int getRound() {
		return round;
	}

	/**
	 * Increase the game round
	 */
	public int nextRound() {
		return this.round++;
	}
}
