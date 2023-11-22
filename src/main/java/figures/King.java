package figures;

import core.Board;
import data.Enum;
import ui.FigureInteraction;

public class King extends Figure
{
    public King(int player, int startingX, int startingY, FigureInteraction figureInteraction, Board board) {
        super(Enum.QUEEN, player, startingX, startingY, (player == 0) ?  "♔" : "♚", figureInteraction, board);
        this.moveSet = new int[][]{{1, 1}, {-1, 1}, {1, -1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    }


    @Override
    public void generateMoves() {
        for (int[] mov : moveSet) {
            commonMove(false, mov);
        }
    }
}
