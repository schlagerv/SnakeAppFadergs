package com.fadergs.snakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.fadergs.snakeapp.classess.OnSwipeTouchListener;
import com.fadergs.snakeapp.engine.GameEngine;
import com.fadergs.snakeapp.enums.Direction;
import com.fadergs.snakeapp.enums.GameState;
import com.fadergs.snakeapp.views.SnakeView;

public class MainActivity extends AppCompatActivity {
    private GameEngine gameEngine;
    private SnakeView snakeView;
    private final Handler handler = new Handler();
    private final long updateDelay = 125;
    private TextView points;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameEngine = new GameEngine();
        gameEngine.initGame();
        snakeView = (SnakeView) findViewById(R.id.snakeView);
        points = findViewById(R.id.pointsTxt);

        snakeView.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public  void onSwipeBottom(){
                gameEngine.updateDirection(Direction.South);

            }
            @Override
            public  void onSwipeLeft(){
                gameEngine.updateDirection(Direction.West);

            }
            @Override
            public  void onSwipeRight(){
                gameEngine.updateDirection(Direction.East);

            }
            @Override
            public  void onSwipeTop(){
                gameEngine.updateDirection(Direction.North);

            }
        });

        startUpdateHandler();
    }

    private void startUpdateHandler() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameEngine.update();
                points.setText("Points: "+ gameEngine.getScore());

                if (gameEngine.getCurrentGameState() == GameState.Running) {
                    handler.postDelayed(this, updateDelay);
                }
                if(gameEngine.getCurrentGameState() == GameState.Lost)
                {
                   onGameLost();

                }

                snakeView.setSnakeViewMap(gameEngine.getMap());
                snakeView.invalidate();
            }
        }, updateDelay);
    }
    private void onGameLost(){
        Toast.makeText(this,"You lost.", Toast.LENGTH_SHORT).show();

       // gameEngine.resetGame();
    }
}
