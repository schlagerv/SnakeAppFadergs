package com.fadergs.snakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameEngine = new GameEngine();
        gameEngine.initGame();
        snakeView = (SnakeView) findViewById(R.id.snakeView);
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
    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                prevX = event.getX();
//                prevY = event.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                float newX = event.getX();
//                float newY = event.getY();
//                break;
//        }
//
//
//        return true;
//    }
}
