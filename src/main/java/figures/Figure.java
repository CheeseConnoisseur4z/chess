package figures;

import ui.FigureInteraction;
import ui.Graphics;
import data.Enum;
import data.Move;
import data.Position;
import core.Board;

import javax.swing.*;
import java.util.ArrayList;


public abstract class Figure
{
    Enum name;
    final public int player;
    public JLabel display;
    public int[][] moveSet;

    public ArrayList<Move> legalMoves = new ArrayList<>();
    public Position pos;
    public int moveCounter = 0;

    Board board;


    public Figure(Enum name, int player, int startingX, int startingY, String labelDisplay, FigureInteraction figureInteraction, Board board) {
        this.name = name;
        this.player = player;
        this.pos = new Position(startingX, startingY);
        this.board = board;
        display = Graphics.createFigureDisplay(pos.x, pos.y, labelDisplay, player);

        figureInteraction.onDragMoveStart(this);
        figureInteraction.dragMove(this);
        figureInteraction.onDragMoveEnd(this);
    }


    public abstract void generateMoves();


    /*
    used for generating moves in a sequence (can also be used for knight, hence variable repeatable)
     */
    public void commonMove(boolean repeatable, int[] mov) {
        Position savePos = new Position(pos.x, pos.y);

        changePosition(mov[0], mov[1]);
        if (!board.inBounds(pos)) {
            pos = new Position(savePos.x, savePos.y);
            return;
        }

        do {
            Move move = new Move();

            if (board.notNull(pos)) {
                if (board.boardAt(pos).player != player) {
                    move.setEaten(board.boardAt(pos));
                    legalMoves.add(move);
                    board.getTile(pos).attackers.add(this);
                }
                break;
            }

            move.setMoveTo(pos);
            legalMoves.add(move);
            board.getTile(pos).attackers.add(this);

            changePosition(mov[0], mov[1]);
        } while (repeatable && board.inBounds(pos));

        setPosition(savePos);
    }


    public void changePosition(int x, int y) {
        pos.x += x;
        pos.y += y;
    }

    public void setPosition(Position pos) {
        this.pos.x = pos.x;
        this.pos.y = pos.y;
    }
}