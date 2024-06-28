package com.example.tictacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainBoard extends AppCompatActivity {

    private TicTacToeB ticTacToeB;
    TextView Pturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_board);
        Pturn = findViewById(R.id.Playertxt);
        Button playAgain = findViewById(R.id.AginBtn);
        playAgain.setVisibility(View.GONE);
        Button HomeBTN = findViewById(R.id.Homebtn);
        HomeBTN.setVisibility(View.GONE);
        String[] PNames = getIntent().getStringArrayExtra("PLAYER_NAME");

        if(PNames != null){
            Pturn.setText((PNames[0] + "Turn"));
        }

        ticTacToeB = findViewById(R.id.ticTacToeB);
        ticTacToeB.setupgame(playAgain , HomeBTN , Pturn , PNames);
    }

    public void AgainClick(View view){
        ticTacToeB.resetgame();
        ticTacToeB.invalidate();
    }
    public void HomeClick(View view){
        Intent in = new Intent(MainBoard.this , MainActivity.class);
        startActivity(in);
    }
}