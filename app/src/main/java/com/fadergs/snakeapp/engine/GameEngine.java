package com.fadergs.snakeapp.engine;

import com.fadergs.snakeapp.classess.Cordinate;
import com.fadergs.snakeapp.enums.TileType;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    public static final int GameWidth = 28;
    public static final int GameHeight = 42;

    private List<Cordinate> walls = new ArrayList<>();

    public GameEngine() {

    }

    public void initGame() {
        addWalls();
    }

    public TileType[][] getMap() {
        TileType[][] map = new TileType[GameWidth][GameHeight];

        for (int x=0; x<GameWidth;x++) {
            for (int y=0;y<GameHeight;y++){
                map[x][y] = TileType.Nothing;
            }
        }

        for (Cordinate wall : walls) {
            map[wall.getX()][wall.getY()] = TileType.Wall;
        }

        return map;
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
