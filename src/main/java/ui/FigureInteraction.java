package ui;

import core.Board;
import data.GameData;
import data.Position;
import figures.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FigureInteraction
{
    Board board;
    GameData gameData;
    JPanel[][] moveOverlay;

    Position mousePosition;


    public FigureInteraction(Board board, JPanel[][] moveOverlay, GameData gameData) {
        this.board = board;
        this.moveOverlay = moveOverlay;
        this.gameData = gameData;
    }


    public void onDragMoveStart(Figure figure) {
        figure.display.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lightMoveOverlay(figure, figure.player == gameData.player);
            }
        });
    }


    public void dragMove(Figure figure) {
        figure.display.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mousePosition = getMousePosition();
                figure.display.setBounds(mousePosition.x - 25, mousePosition.y - 50, figure.display.getWidth(), figure.display.getHeight());
            }
        });
    }


    public void onDragMoveEnd(Figure figure) {
        figure.display.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                for (int i = 0; i < 8; i++) {
                    for (int i2 = 0; i2 < 8; i2++) {
                        moveOverlay[i][i2].setVisible(false);
                    }
                }

                mousePosition.x /= 50;
                mousePosition.y = 7 - (mousePosition.y - 25) / 50;

                if (figure.player == gameData.player && board.tiles[mousePosition.x][mousePosition.y].attackers.contains(figure)) {
                    gameData.game.makeMove(figure, mousePosition);
                } else {
                    gameData.game.setDisplayPosition(figure, figure.pos);
                }
            }
        });
    }


    private void lightMoveOverlay(Figure figure, boolean rightPlayer) {
        figure.legalMoves.forEach(move -> {
            Position pos = move.getMoveTo();
            moveOverlay[pos.x][pos.y].setVisible(true);
            if (board.notNull(pos) && board.boardAt(pos).player != figure.player) {
                moveOverlay[pos.x][pos.y].setBackground((rightPlayer) ? new Color(0xB5FF0000, true) : new Color(0xB58D8D8D, true));
            } else {
                moveOverlay[pos.x][pos.y].setBackground((rightPlayer) ? new Color(0xB514FF00, true) : new Color(0xB58D8D8D, true));
            }
        });
        moveOverlay[figure.pos.x][figure.pos.y].setVisible(true);
        moveOverlay[figure.pos.x][figure.pos.y].setBackground(new Color(0xB5004CFF, true));
    }


    private Position getMousePosition() {
        int mouseX = (int)(MouseInfo.getPointerInfo().getLocation().getX() - Graphics.frame.getLocation().getX());
        int mouseY = (int)(MouseInfo.getPointerInfo().getLocation().getY() - Graphics.frame.getLocation().getY());
        return new Position(mouseX, mouseY);
    }
}
