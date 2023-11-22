package ui;

import data.Tile;

import javax.swing.*;
import java.awt.*;

public class Graphics
{
    public static JFrame frame = makeFrame();


    static JFrame makeFrame() {
        JFrame frame = new JFrame("Chess");
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0xFFF4D8));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        return frame;
    }

    public static JPanel createTileDisplay(int x, int y) {
        JPanel tile = new JPanel();
        tile.setBounds(x * 50, y * 50, 51, 51);
        tile.setBackground(((x + y) % 2 != 0) ? new Color(0x795934) : new Color(0xFFC88E));
        return tile;
    }

    public static void addAllTileDisplay(Tile[][] board, boolean add) {
        for (Tile[] row : board) {
            for (Tile tile : row) {
                if (add) {
                    frame.add(tile.display);
                } else {
                    frame.remove(tile.display);
                }
            }
        }
    }

    public static JLabel createFigureDisplay(int x, int y, String display, int player) {
        JLabel figure = new JLabel();
        figure.setBounds(x * 50, 350 - y * 50, 50, 50);
        figure.setText(display);
        figure.setFont(new Font("figure icon font", Font.PLAIN, 50));
        if (player == 0) {
            figure.setForeground(Color.WHITE);
        }
        frame.add(figure);
        return figure;
    }

    public static JPanel[][] createMoveOverlay() {
        JPanel[][] moveOverlay = new JPanel[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                moveOverlay[x][y] = new JPanel();
                moveOverlay[x][y].setBounds(x * 50, 350 - y * 50, 51, 51);
                moveOverlay[x][y].setBackground(new Color(0xB514FF00, true));
                moveOverlay[x][y].setVisible(false);
            }
        }
        return moveOverlay;
    }

    public static void addMoveOverlayToFrame(JPanel[][] moveOverlay, boolean add) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (add) {
                    frame.add(moveOverlay[x][y]);
                } else {
                    frame.remove(moveOverlay[x][y]);
                }
            }
        }
    }
}
