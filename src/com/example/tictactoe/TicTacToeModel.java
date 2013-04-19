package com.example.tictactoe;

/**
 * This class checks to see if anybody has won as well as changing the turn to
 * the other player if no winner has been decided
 * 
 * @author Matthew
 * 
 */
public class TicTacToeModel {

	public enum GameState {
		PLAYER_1_WINS, PLAYER_2_WINS, STALEMATE, IN_PROGRESS
	};

	private boolean isCPU;
	private int currentPlayerTurn;
	private int[] boardState;
	private GameState gameState;
	private int[][] win = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 },
			{ 2, 5, 8 }, { 3, 6, 9 }, { 1, 5, 9 }, { 3, 5, 7 } };

	/**
	 * Constructor for New Game
	 */
	public TicTacToeModel() {
		currentPlayerTurn = 1;
		boardState = new int[9];
		for (int i = 0; i < 9; i++) {
			boardState[i] = 0;
		}
		gameState = GameState.IN_PROGRESS;
	}

	/**
	 * Constructor for Loaded Game
	 * 
	 * @param currentPlayerTurn
	 * @param boardState
	 * @param isCPU
	 * @param gameState
	 */
	public TicTacToeModel(int currentPlayerTurn, int[] boardState,
			boolean isCPU, GameState gameState) {
		this.currentPlayerTurn = currentPlayerTurn;
		this.isCPU = isCPU;
		for (int i = 0; i < 9; i++) {
			this.boardState[i] = boardState[i];
		}
		this.gameState = gameState;
	}

	public int getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setIsCPU(boolean opp) {
		isCPU = opp;
	}

	public boolean getIsCPU() {
		return isCPU;
	}

	public int[] getBoardState() {
		return boardState;
	}

	public GameState getGameState() {
		return gameState;
	}

	/**
	 * This method sets the tile to the current player's value It calls
	 * checkPlayerWon to see if the player won If not, it sets the current
	 * player equal to the opponent
	 * 
	 * @param buttonIndex
	 * @return
	 */
	public GameState turn(int buttonIndex) {
		boardState[buttonIndex] = currentPlayerTurn;
		this.checkPlayerWon();
		if (currentPlayerTurn == 1) {
			currentPlayerTurn = 2;
		} else {
			currentPlayerTurn = 1;
		}

		return gameState;
	}

	/**
	 * Checks to see if the current player has won on this turn
	 */
	private void checkPlayerWon() {
		for (int[] winCondition : win) {
			boolean didPlayerWin = true;
			for (int square : winCondition) {
				if (boardState[square - 1] != currentPlayerTurn) {
					didPlayerWin = false;
					break;
				}
			}

			if (didPlayerWin) {
				if (currentPlayerTurn == 1) {
					gameState = GameState.PLAYER_1_WINS;
				} else {
					gameState = GameState.PLAYER_2_WINS;
				}
			}
		}
		if (gameState == GameState.IN_PROGRESS) {
			boolean stale = true;
			for (int i = 0; i < 9; i++) {
				if (boardState[i] == 0) {
					stale = false;
				}
			}

			if (stale) {
				gameState = GameState.STALEMATE;
			}
		}
	}

	public void onExit() {

	}

}
