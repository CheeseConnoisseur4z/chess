package core;

import data.GameData;
import data.Position;
import figures.Figure;
import ui.Graphics;

public class Game
{
    Board board;
    final GameData gameData = new GameData(this);


    public void start() {
        board = new Board(gameData);
        gameGraphics();
    }


    private void  gameGraphics() {
        Graphics.frame.setSize(414, 436);
        Graphics.addAllTileDisplay(board.tiles, true);
        Graphics.frame.setSize(413, 435);
    }


    public void makeMove(Figure figure, Position to) {
        if (figure != null && to != null) {
            board.moveFigure(figure, to);
        }
        turnChange();
        board.generateMoveAllFigures();
    }


    private void turnChange() {
        gameData.player = (gameData.player == 0) ? 1 :  0;
        gameData.turn++;
    }


    public void setDisplayPosition(Figure figure, Position pos) {
        figure.display.setBounds(pos.x * 50, 350 - pos.y * 50, figure.display.getWidth(), figure.display.getHeight());
    }
}
