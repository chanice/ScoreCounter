package com.example.midni.scorecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.support.annotation.NonNull;



import org.w3c.dom.Text;

public class WinnerActivity extends AppCompatActivity {

    private TextView winningMessage;
    private String winningTeam;
    private int winScore;
    private static final String TAG = "WinnerActivity";
    private Button shareMessage;
    private Button shareCall;
    private Uri mapLocation;
    private Button map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        Intent intent = getIntent();
        mapLocation = Uri.parse("geo:37.7749,-122.4192?q=" + Uri.encode("Badminton near me"));
        winScore = (Integer) intent.getExtras().get(MainActivity.KEYSCORE);
        winningTeam = intent.getExtras().get(MainActivity.KEYWINNER).toString();
        winningMessage = findViewById(R.id.winnerText);
        winningMessage.setText("The winning team is " + winningTeam + " by " + winScore + " point(s)");
        shareMessage = findViewById(R.id.textShareButton);
        shareMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareMessageActivity();
            }
        });
        shareCall = findViewById(R.id.callShareButton);
        shareCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareCallActivity();
            }
        });
        map = findViewById(R.id.mapButton);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMap(mapLocation);
            }
        });
    }

    public void shareMessageActivity() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "My team won badminton by " + winScore + " points!");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void shareCallActivity() {

        Log.d(TAG, "INSIDE SHARE CALL");
        Intent callIntent = new Intent(Intent.ACTION_CALL);
//        intent.setAction();
        callIntent.setData(Uri.parse("tel:0377778888"));
        if (callIntent.resolveActivity(getPackageManager()) != null) {

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.CALL_PHONE}, 1);
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

            }
        } else {
            startActivity(callIntent);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "inside onRequestPermissionsResult");
        switch (requestCode){
            case 1:{
                if((grantResults.length > 0) &&
                        (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    shareCallActivity();
                }
            }
        }
    }
}

