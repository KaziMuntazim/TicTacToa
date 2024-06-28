package com.example.tictacto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tictacto.R;

public class TicTacToeB extends View {


    private final int BoardColor;
    private final int XColor;
    private final int OColor;
    private final int winningLineColor;
    private final GameLogic game;
    private boolean winningLine = false;

    private final Paint paint  = new Paint();

    private int cellSize = getWidth()/3;
    public TicTacToeB(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs , R.styleable.TicTacToeB , 0 , 0);
        game = new GameLogic();
        try {
            BoardColor = a.getInteger(R.styleable.TicTacToeB_BoardColor , 0);
            XColor = a.getInteger(R.styleable.TicTacToeB_XColor , 0);
            OColor = a.getInteger(R.styleable.TicTacToeB_OClolor , 0);
            winningLineColor = a.getInteger(R.styleable.TicTacToeB_winningLineColor , 0);
        }finally {
            a.recycle();
        }
    }

    @Override
    protected  void onMeasure(int width , int height){
        super.onMeasure(width , height);
        int dimenson = Math.min(getMeasuredWidth() , getMeasuredHeight());
        cellSize = dimenson/3;
        setMeasuredDimension(dimenson , dimenson);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drawgameboard(canvas);
        drawMarkesr(canvas);
        if(winningLine){
            paint.setColor(winningLineColor);
            drawWinningLines(canvas);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y/cellSize);
            int col = (int) Math.ceil(x/cellSize);

            if(!winningLine){
                if (game.upGameboard(row , col)){
                    invalidate();

                    if (game.WinnerCheak()){
                        winningLine = true;
                        invalidate();
                    }


                    if (game.getPlayer() % 2 == 0){
                        game.setPlayer(game.getPlayer() - 1);
                    }else {
                        game.setPlayer(game.getPlayer() + 1);
                    }
                }
            }
            invalidate();
            return true;
        }
        return false;
    }

    private void drawgameboard(Canvas canvas){
        paint.setColor(BoardColor);
        paint.setStrokeWidth(16);
        for (int c = 1; c < 3 ; c++ ){
            canvas.drawLine(cellSize*c , 0 , cellSize*c , canvas.getWidth() , paint);
        }
        for (int r = 1; r < 3 ; r++){
            canvas.drawLine(0 , cellSize*r , canvas.getWidth()  , cellSize*r, paint);
        }
    }
    private void drawMarkesr(Canvas canvas){
        for (int r = 0 ; r < 3 ; r++ ){
            for (int c = 0 ; c < 3 ; c++){
                if (game.getGamebord()[r][c] != 0){
                    if (game.getGamebord()[r][c] == 1){
                        drawX(canvas , r , c);
                    }else {
                        drawO(canvas , r , c);
                    }
                }
            }
        }
    }

    private void drawX(Canvas canvas , int row , int col){
        paint.setColor(XColor);
        canvas.drawLine( (float) ((col+1)*cellSize - cellSize*0.2) ,
                         (float) (row*cellSize + cellSize*0.2),
                         (float) (col*cellSize + cellSize*0.2) ,
                         (float) ((row+1)*cellSize - cellSize*0.2),
                         paint);

        canvas.drawLine( (float) (col*cellSize + cellSize*0.2),
                         (float) (row*cellSize + cellSize*0.2),
                         (float) ((col+1)*cellSize - cellSize*0.2),
                         (float) ((row+1)*cellSize - cellSize*0.2),
                         paint);
    }
    private void drawO(Canvas canvas , int row , int col){
        paint.setColor(OColor);
        canvas.drawOval((float) (col*cellSize + cellSize * 0.2),
                        (float) (row*cellSize + cellSize * 0.2),
                        (float) ((col * cellSize + cellSize) - cellSize * 0.2),
                        (float) ((row * cellSize + cellSize) - cellSize * 0.2),
                        paint);
    }

    private void drawHoLine(Canvas canvas , int row , int col){
        canvas.drawLine(col , row*cellSize + (float)cellSize/2 , cellSize*3 , row*cellSize + (float)cellSize/2 , paint);
    }
    private void drawVarLine(Canvas canvas , int row , int col){
        canvas.drawLine(col*cellSize + (float)cellSize/2 , row , col*cellSize + (float)cellSize/2 , cellSize*3 , paint);
    }
    private void drawDiaLine(Canvas canvas){
        canvas.drawLine(0 , cellSize*3 , cellSize*3 , 0 , paint);
    }
    private void drawDiaLineNeg(Canvas canvas){
        canvas.drawLine(0 ,0 , cellSize*3 , cellSize*3 , paint);
    }
    private void drawWinningLines(Canvas canvas){
        int row = game.getWinType()[0];
        int col = game.getWinType()[1];

        switch (game.getWinType()[2]){
            case 1:
                drawHoLine(canvas , row , col);
                break;
            case 2:
                drawVarLine(canvas , row , col);
                break;
            case 3:
                drawDiaLineNeg(canvas);
                break;
            case 4:
                drawDiaLine(canvas);
                break;
        }
    }
    public void setupgame(Button playA , Button Hom , TextView Pdisplay , String[] names){
        game.setPlayAG(playA);
        game.setHomP(Hom);
        game.setPlayerTurn(Pdisplay);
        game.setPname(names);
    }
    public void resetgame(){
        game.resetgame();
        winningLine = false;
    }
}

