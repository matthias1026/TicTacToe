package com.example.tictactoe;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.tictactoe.TicTacToeModel.GameState;

/**
 * This is the activity in which the game is played.
 * 
 * @author Matthew
 * 
 */
public class GameActivity extends Activity {

	private TicTacToeModel model;
	private int new_old;
	GameData gameData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		this.model = new TicTacToeModel();
		Intent intent = getIntent();
		model.setIsCPU(intent.getBooleanExtra(SelectActivity.EXTRA_MESSAGE,
				SelectActivity.b));
		new_old = intent.getIntExtra(SelectActivity.EXTRA_MESSAGE,
				SelectActivity.new_old);
		gameData = new GameData(this);
		if (new_old == 0) {
			gameData.insert(model);
		}
		
		/*
		 * else
		 * ReadDB where game_id = button tag
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}

	/**
	 * Based on button was clicked and whose turn it is this method will turn the
	 * button into an x or o
	 * 
	 * @param v
	 */
	public void tileClicked(View v) {

		if (model.getCurrentPlayerTurn() == 1) {
			((Button) v).setText("    X    ");
		} else {
			((Button) v).setText("    O    ");
		}
		((Button) v).setClickable(false);
		String state = "";
		GameState gs = model.turn(Integer.parseInt((String) v.getTag()));
		switch (gs) {
		case PLAYER_1_WINS:
			state = "Player 1 WINS!";
			break;
		case PLAYER_2_WINS:
			state = "Player 2 WINS!";
			break;
		case STALEMATE:
			state = "STALEMENT!";
			break;
		}

		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		if (state.length() > 0) {
			alert.setTitle("WOO!");
			alert.setMessage(state);

			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							GameActivity.this.finish();
						}
					});
			alert.show();
		} else {
			if (model.getIsCPU() && model.getCurrentPlayerTurn() == 2) {
				cpuTurn();
			}
		}

	}

	/**
	 * This method operates when playing against the CPU and performs the CPU's
	 * turn.
	 */
	public void cpuTurn() {
		Random r;
		int random;

		r = new Random();
		random = r.nextInt(9);
		switch (random) {
		case 0:
			if (findViewById(R.id.t0).isClickable()) {
				findViewById(R.id.t0).performClick();
			} else {
				cpuTurn();
			}
			break;
		case 1:
			if (findViewById(R.id.t1).isClickable()) {
				findViewById(R.id.t1).performClick();
			} else {
				cpuTurn();
			}
			break;
		case 2:
			if (findViewById(R.id.t2).isClickable()) {
				findViewById(R.id.t2).performClick();
			} else {
				cpuTurn();
			}
			break;
		case 3:
			if (findViewById(R.id.t3).isClickable()) {
				findViewById(R.id.t3).performClick();
			} else {
				cpuTurn();
			}
			break;
		case 4:
			if (findViewById(R.id.t4).isClickable()) {
				findViewById(R.id.t4).performClick();
			} else {
				cpuTurn();
			}
			break;
		case 5:
			if (findViewById(R.id.t5).isClickable()) {
				findViewById(R.id.t5).performClick();
			} else {
				cpuTurn();
			}
			break;
		case 6:
			if (findViewById(R.id.t6).isClickable()) {
				findViewById(R.id.t6).performClick();
			} else {
				cpuTurn();
			}
			break;
		case 7:
			if (findViewById(R.id.t7).isClickable()) {
				findViewById(R.id.t7).performClick();
			} else {
				cpuTurn();
			}
			break;
		case 8:
			if (findViewById(R.id.t8).isClickable()) {
				findViewById(R.id.t8).performClick();
			} else {
				cpuTurn();
			}
			break;

		}

	}

	/**
	 * Upon leaving the game screen this method will call updateGameDB in
	 * GameData to update the game table in the DB
	 */
	public void exitGame() {
		GameData.updateGameDB(model);
	}

}
