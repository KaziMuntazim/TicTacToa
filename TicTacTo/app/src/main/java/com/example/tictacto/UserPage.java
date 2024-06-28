package com.example.tictacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserPage extends AppCompatActivity {

    EditText PL1 ,PL2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        PL1 = findViewById(R.id.PL1);
        PL2 = findViewById(R.id.PL2);
    }

    public void PlayBtn(View view){

        String player1 = PL1.getText().toString();
        String player2 = PL2.getText().toString();
        Intent in = new Intent(UserPage.this , MainBoard.class);
        in.putExtra("PLAYER_NAME" , new String[] {player1 , player2});
        startActivity(in);
    }
}