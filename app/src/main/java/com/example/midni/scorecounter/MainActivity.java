package com.example.midni.scorecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView Team1Score;
    private TextView Team2Score;
    private int count1 = 0;
    private int count2 = 0;
    private Button increase1;
    private Button increase2;
    private int winDifference;
    private static final String TAG = "MainActivity";
    protected static final String KEYWINNER  = "Winner";
    protected static final String KEYSCORE = "Points";
    private String winningTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Team1Score = findViewById(R.id.teamOneScore);
        Team2Score = findViewById(R.id.teamTwoScore);
        increase1 = findViewById(R.id.teamOneButton);
        increase2 = findViewById(R.id.teamTwoButton);
        increase1.setOnClickListener((View.OnClickListener)this);
        increase2.setOnClickListener((View.OnClickListener)this);

        Team1Score.setText(""+count1);
        Team2Score.setText(""+count2);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onClick(View view){
        Log.d(TAG,"ONCLICK");
        if(increase1.equals(view)){

            count1++;
            if(count1 == 5){
                winningTeam = "Team 1";
                calculateWinDifference(count2);
                nextActivity(view);

            }
            Team1Score.setText(""+count1);
        }
    else if(increase2.equals(view)){
            Log.d(TAG,"score2 increase");
            count2++;
            if(count2 == 5){
                winningTeam = "Team2";
                calculateWinDifference(count1);
                nextActivity(view);

            }
            updateScore();
        }
    }
    public void updateScore(){
        Log.d(TAG,"UPDATE SCORE METHOD");

        Team2Score.setText(""+count2);
    }
    public void calculateWinDifference(int loserScore){
        winDifference = 5-loserScore;
    }

//    @Override
//    protected void onRestoreInstanceState(BundlesavedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        count1 = savedInstanceState.getInt("", count1);
//        count2 = savedInstanceState.getInt("", count2);
//    }

    public void nextActivity(View view){
        Intent intent = new Intent(this,WinnerActivity.class);
        intent.putExtra(KEYWINNER,winningTeam);
        intent.putExtra(KEYSCORE,winDifference);
        startActivity(intent);

    }
}
