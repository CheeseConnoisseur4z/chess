package data;

import figures.Figure;
import ui.Graphics;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;


public class Tile
{
    public Figure figure;
    public ArrayList<Figure> attackers = new ArrayList<>();
    public JPanel display;

    public Tile(int x, int y) {
        this.display = Graphics.createTileDisplay(x, y);
    }
}
