package com.example.midni.scorecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WinnerActivity extends AppCompatActivity {

    private TextView winningMessage;
    private String winningTeam;
    private static final String TAG = "WinnerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        Intent intent = getIntent();
        winningTeam = intent.getExtras().get(MainActivity.KEYWINNER).toString();
        winningMessage = findViewById(R.id.winnerText);
        winningMessage.setText("The winning Team is "+winningTeam);
    }
}
