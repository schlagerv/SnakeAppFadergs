package com.fadergs.snakeapp.engine;

import com.fadergs.snakeapp.classess.Cordinate;
import com.fadergs.snakeapp.enums.Direction;
import com.fadergs.snakeapp.enums.GameState;
import com.fadergs.snakeapp.enums.TileType;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    public static final int GameWidth = 28;
    public static final int GameHeight = 42;

    private List<Cordinate> walls = new ArrayList<>();
    private List<Cordinate> snake = new ArrayList<>();

    private Direction currentDirection = Direction.East;

    private GameState currentGameState = GameState.Running;


    public GameEngine() {

    }

    public void initGame() {
        addSnake();
        addWalls();
    }

    public void update() {
        switch (currentDirection) {
// Update Snake
            case North:
                updateSnake(0, -1);
                break;
            case East:
                updateSnake(1, 0);
                break;
            case South:
                updateSnake(0, 1);
                break;
            case West:
                updateSnake(-1, 0);
                break;
        }

        // Check wall collision
        for (Cordinate w : walls) {
            if (snake.get(0).equals(w)) {
                currentGameState = GameState.Lost;
            }

        }
    }

    public void addSnake() {
        snake.clear();
        snake.add(new Cordinate(7, 7));
        snake.add(new Cordinate(6, 7));
        snake.add(new Cordinate(5, 7));
        snake.add(new Cordinate(4, 7));
        snake.add(new Cordinate(3, 7));
        snake.add(new Cordinate(2, 7));
    }


    public TileType[][] getMap() {
        TileType[][] map = new TileType[GameWidth][GameHeight];

        for (int x = 0; x < GameWidth; x++) {
            for (int y = 0; y < GameHeight; y++) {
                map[x][y] = TileType.Nothing;
            }
        }

        for (Cordinate s : snake) {
            map[s.getX()][s.getY()] = TileType.SnakeTail;
        }
        map[snake.get(0).getX()][snake.get(0).getY()] = TileType.SnakeHead;

        for (Cordinate wall : walls) {
            map[wall.getX()][wall.getY()] = TileType.Wall;
        }

        return map;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }


    private void updateSnake(int x, int y) {
        for (int i = snake.size() - 1; i > 0; i--) {
            snake.get(i).setX(snake.get(i - 1).getX());
            snake.get(i).setY(snake.get(i - 1).getY());
        }
        snake.get(0).setX(snake.get(0).getX() + x);
        snake.get(0).setY(snake.get(0).getY() + y);
    }

    public void updateDirection(Direction newDirection) {
        if (Math.abs(newDirection.ordinal() - currentDirection.ordinal()) %2 ==1){
            currentDirection = newDirection;
        }
    }

    private void addWalls() {
        for (int x = 0; x < GameWidth; x++) {
            walls.add(new Cordinate(x, 0));
            walls.add(new Cordinate(x, GameHeight - 1));
        }

        //Left and Right Walls

        for (int y = 1; y < GameHeight; y++) {
            walls.add(new Cordinate(0, y));
            walls.add(new Cordinate(GameWidth - 1, y));
        }
    }

}
