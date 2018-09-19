package com.example.midni.scorecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextView Team1Score;
    private TextView Team2Score;
    private int count1 = 0;
    private int count2 = 0;
    private Button increase1;
    private Button increase2;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Team1Score = findViewById(R.id.teamOneScore);
        Team2Score = findViewById(R.id.teamTwoScore);
        increase1 = findViewById(R.id.teamOneButton);
        increase2 = findViewById(R.id.teamTwoButton);

        Team1Score.setText(""+count1);
        Team2Score.setText(""+count2);

    }
    public void onClick(View view){
        if(increase1.equals(view)){
            count1++;
            updateScore();
        }
    else if(increase2.equals(view)){
            count2++;
            updateScore();
        }
    }
    public void updateScore(){
        Team1Score.setText(""+count1);
        Team2Score.setText(""+count2);
    }

}
