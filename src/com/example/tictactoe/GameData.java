package com.example.tictactoe;

import com.example.tictactoe.TicTacToeModel.GameState;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class is in charge of all the database activity
 * 
 * @author Matthew
 * 
 */
public class GameData {
	static final String TAG = "GameData";
	public static final String DB_NAME = "tictactoe.db";
	public static final int DB_VERSION = 1;
	public static final String TABLE = "game";
	public static final String C_GAMEID = "game_id";
	public static final String C_IS_CPU = "is_cpu";
	public static final String C_BOARDSTATE = "board_state";
	public static final String C_WINNER = "winner";
	public static final String C_CURRENT_PLAYER_TURN = "current_player_turn";

	Context context;
	DbHelper dbHelper;
	SQLiteDatabase db;

	public GameData(Context context) {
		this.context = context;
		dbHelper = new DbHelper();
	}

	/**
	 * Adds a new row to the 'game' table
	 * 
	 * @param model
	 */
	public void insert(TicTacToeModel model) {
		db = dbHelper.getWritableDatabase();
		int gs = 0;
		
		/*if (model.getGameState() == GameState.IN_PROGRESS) {
			gs = 0;
		}
		if (model.getGameState() == GameState.PLAYER_1_WINS) {
			gs = 1;
		}
		if (model.getGameState() == GameState.PLAYER_2_WINS) {
			gs = 2;
		}
		if (model.getGameState() == GameState.STALEMATE) {
			gs = 3;
		}*/

		db.execSQL("Insert into game (is_cpu, board_state, winner, current_player_turn) values ("
				+ model.getIsCPU()
				+ ","
				+ model.getBoardState()
				+ ","
				+ gs
				+ "," + model.getCurrentPlayerTurn() + ");");
	}

	/**
	 * Updates an existing game's row with new data
	 * 
	 * @param model
	 */
	public static void updateGameDB(TicTacToeModel model) {

	}

	class DbHelper extends SQLiteOpenHelper {

		public DbHelper() {
			super(context, DB_NAME, null, DB_VERSION);
		}

		/**
		 * Creates a database table called 'game'
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = String
					.format("create table %s"
							+ "(%s int primary key, %s boolean, %s int, %s int, %s int)",
							TABLE, C_GAMEID, C_IS_CPU, C_BOARDSTATE, C_WINNER,
							C_CURRENT_PLAYER_TURN);
			Log.d(TAG, "onCreate with SQL: " + sql);
			db.execSQL(sql);

		}

		/**
		 * Updates the table if any columns are added
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop if exists " + TABLE);
			onCreate(db);
		}

	}
}