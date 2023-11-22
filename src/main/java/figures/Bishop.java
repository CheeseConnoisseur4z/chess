package figures;

import core.Board;
import data.Enum;
import ui.FigureInteraction;

public class Bishop extends Figure
{
    public Bishop(int player, int startingX, int startingY, FigureInteraction figureInteraction, Board board) {
        super(Enum.BISHOP, player, startingX, startingY, (player == 0) ? "♗" : "♝", figureInteraction, board);
        this.moveSet = new int[][]{{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    }


    @Override
    public void generateMoves() {
        for (int[] mov : moveSet) {
            commonMove(true, mov);
        }
    }
}
