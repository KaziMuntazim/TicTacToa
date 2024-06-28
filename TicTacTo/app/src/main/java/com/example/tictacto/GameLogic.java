package com.example.tictacto;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {
    private int [][] gamebord;
    private String [] Pname = {"Player1 , player2"};
    private int[] WinType = {-1 , -1 , -1};
     Button PlayAG;
    private Button HomP;
    private TextView playerTurn;
    private int player = 1;
    GameLogic(){
        gamebord = new int[3][3];
        for (int r = 0 ; r < 3 ; r++ ){
            for (int c = 0 ; c < 3 ; c++){
                gamebord[r][c] = 0;
            }
        }
    }
    public boolean upGameboard(int row , int col){
        if (gamebord[row -1][col-1] == 0){
            gamebord[row-1][col-1] = player;

            if (player == 1){
                playerTurn.setText((Pname[1] + "Turn"));
            }else {
                playerTurn.setText((Pname[0] + "Turn"));
            }
            return true;
        }else {
            return false;
        }
    }

    public boolean WinnerCheak(){
        boolean isWin = false;
        for (int r = 0 ; r < 3 ; r++){
            if (gamebord[r][0] == gamebord[r][1] && gamebord[r][0] == gamebord[r][2] && gamebord[r][0] != 0){
                WinType = new int[]{r , 0 , 1};
                isWin = true;
            }
        }
        for (int c = 0 ; c < 3 ; c++){
            if (gamebord[0][c] == gamebord[1][c] && gamebord[2][c] == gamebord[0][c] && gamebord[0][c] != 0){
                WinType = new int[]{0 , c , 2};
                isWin = true;
            }
        }
        if (gamebord[0][0] == gamebord[1][1] && gamebord[0][0] == gamebord[2][2] && gamebord[0][0] != 0){
            WinType = new int[]{0 , 2 , 3};
            isWin = true;
        }
        if (gamebord[2][0] == gamebord[1][1] && gamebord[2][0] == gamebord[0][2] && gamebord[2][0] != 0){
            WinType = new int[]{2 , 2 , 4};
            isWin = true;
        }
        int boardFeld = 0;
        for (int r = 0 ; r < 3 ; r++){
            for(int c = 0 ; c < 3 ; c++){
                if (gamebord[r][c] != 0){
                    boardFeld += 1;
                }
            }
        }
        if (isWin){
            PlayAG.setVisibility(View.VISIBLE);
            HomP.setVisibility(View.VISIBLE);
            playerTurn.setText(Pname[player-1] + "Won....!!!!😎");
            return true;
        } else if (boardFeld == 9) {
            PlayAG.setVisibility(View.VISIBLE);
            HomP.setVisibility(View.VISIBLE);
            playerTurn.setText("Tie Game....😑");
            return true;
        }else {
            return false;
        }
    }
    public void resetgame(){
        for (int r = 0 ; r < 3 ; r++ ){
            for (int c = 0 ; c < 3 ; c++){
                gamebord[r][c] = 0;
            }
        }

        player = 1;
        PlayAG.setVisibility(View.GONE);
        HomP.setVisibility(View.GONE);
        playerTurn.setText((Pname[0] + "Turn"));
    }

    public void setPlayAG(Button playAG) {
        this.PlayAG = playAG;
    }

    public void setHomP(Button homP) {
        this.HomP = homP;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPname(String[] pname) {
        this.Pname = pname;
    }

    public int[][] getGamebord() {
        return gamebord;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public int[] getWinType() {
        return WinType;
    }
}
