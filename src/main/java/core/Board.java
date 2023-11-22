package core;

import data.GameData;
import data.Position;
import data.Tile;
import figures.*;
import ui.FigureInteraction;
import ui.Graphics;

import javax.swing.*;

public class Board
{
    final public Tile[][] tiles = createBoard();
    public JPanel[][] moveOverlay;

    GameData gameData;


    public Board(GameData gameData) {
        this.gameData = gameData;
        addFiguresToBoard();
    }


    public void moveFigure(Figure figure, Position to) {
        gameData.game.setDisplayPosition(figure, to);
        if (notNull(to)) {
            boardAt(to).display.setVisible(false);
        }
        this.ghostMove(figure, to);
        figure.moveCounter++;
    }


    private void ghostMove(Figure figure, Position to) {
        setBoard(null, figure.pos);
        figure.pos = to;
        setBoard(figure, to);
    }


    public Tile[][] createBoard() {
        Tile[][] tiles = new Tile[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tiles[x][y] = new Tile(x, y);
            }
        }

        return tiles;
    }


    public void addFiguresToBoard() {
        moveOverlay = Graphics.createMoveOverlay();
        FigureInteraction figureInteraction = new FigureInteraction(this, moveOverlay, gameData);

        tiles[2][0].figure = new Rook(0, 2, 0, figureInteraction, this);
        tiles[2][4].figure = new Bishop(0, 2, 4, figureInteraction, this);
        tiles[2][7].figure = new King(1, 2, 7, figureInteraction, this);
        tiles[7][7].figure = new Queen(1, 7, 7, figureInteraction, this);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (tiles[x][y].figure != null) {
                    tiles[x][y].figure.generateMoves();
                    Graphics.frame.add(tiles[x][y].figure.display);
                }
            }
        }

        Graphics.addMoveOverlayToFrame(moveOverlay, true);
    }


    public void generateMoveAllFigures() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (tiles[x][y].figure != null) {
                    tiles[x][y].figure.legalMoves.clear();
                    tiles[x][y].attackers.clear();
                    tiles[x][y].figure.generateMoves();
                }
            }
        }
    }


    public Figure boardAt(Position pos) {
        return tiles[pos.x][pos.y].figure;
    }

    public boolean notNull(Position pos) {
        return boardAt(pos) != null;
    }

    public boolean inBounds(Position pos) {
        return pos.x >= 0 && pos.x < 8 && pos.y >= 0 && pos.y < 8;
    }

    public Tile getTile(Position pos) {
        return tiles[pos.x][pos.y];
    }

    public void setBoard(Figure figure, Position pos) {
        tiles[pos.x][pos.y].figure = figure;
    }
}