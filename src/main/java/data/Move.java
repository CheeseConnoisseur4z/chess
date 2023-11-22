package data;

import figures.Figure;

public class Move
{
    Position moveTo;
    Figure eaten;
    int score;

    public Figure setEaten(Figure figure) {
        return eaten = figure;
    }

    public Position setMoveTo(Position pos) {
        return moveTo = new Position(pos.x, pos.y);
    }

    public Position getMoveTo() {
        return moveTo;
    }
}
