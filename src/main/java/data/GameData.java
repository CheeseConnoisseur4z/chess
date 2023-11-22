package data;

import core.Game;

public class GameData
{
    public int player = 0;
    public Game game;
    public int turn = 0;

    public GameData(Game game) {
        this.game = game;
    }
}
