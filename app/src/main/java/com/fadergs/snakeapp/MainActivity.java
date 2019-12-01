package com.fadergs.snakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fadergs.snakeapp.engine.GameEngine;
import com.fadergs.snakeapp.views.SnakeView;

public class MainActivity extends AppCompatActivity {
    private GameEngine gameEngine;
    private SnakeView snakeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameEngine = new GameEngine();
        gameEngine.initGame();
        snakeView = (SnakeView) findViewById(R.id.snakeView);
        snakeView.setSnakeViewMap(gameEngine.getMap());
        snakeView.invalidate();
    }
}
