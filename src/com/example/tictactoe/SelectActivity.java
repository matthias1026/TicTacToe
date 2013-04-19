package com.example.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * SelectActivity is the activity or screen in which the User picks what game they play.
 * 
 * @author Matthew
 *
 */
public class SelectActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.tictactoe.MESSAGE";
	public static boolean b;
	public static int new_old;

/**
 * 
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_select, menu);
		return true;
	}
	/**
	 * After the User clicks the "New Game" button an alert pops up
	 * asking what kind of opponent they want to play against.
	 * 
	 * Then the code determines which "Opponent" button the user clicked and 
	 * will put the user in a game against the selected opponent
	 * 
	 * @param view
	 */

	public void selectOpponent(View view) {
		final Intent intent = new Intent(this, GameActivity.class);
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		{
			alert.setTitle("Select Your Opponent!");

			alert.setPositiveButton("Local Enemy!",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							b = false;
							new_old = 0;
							intent.putExtra(EXTRA_MESSAGE, b);
							intent.putExtra(EXTRA_MESSAGE, new_old);
							startActivity(intent);
						}
					});

			alert.setNegativeButton("Dastardly CPU!",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							b = true;
							new_old = 0;
							intent.putExtra(EXTRA_MESSAGE, b);
							intent.putExtra(EXTRA_MESSAGE, new_old);
							startActivity(intent);
						}
					});

			alert.show();
		}
		
		
	}
}
