package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GridPanel extends JPanel {
    private final int size;
    private final JLabel[][] cells;

    public GridPanel(int size) {
        this.size = size;
        this.cells = new JLabel[size][size];
        setLayout(new GridLayout(size, size, 4, 4));
        setBackground(new Color(240, 240, 240));
        setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        buildCells();
    }

    private void buildCells() {
        Font font = new Font("SansSerif", Font.BOLD, 16);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JLabel label = new JLabel("", SwingConstants.CENTER);
                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setFont(font);
                label.setBorder(new LineBorder(new Color(220, 220, 220)));
                label.setPreferredSize(new Dimension(60, 60));
                cells[row][col] = label;
                add(label);
            }
        }
    }

    public void updateGrid(Position position) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JLabel cell = cells[row][col];
                cell.setText("");
                cell.setBackground(Color.WHITE);
            }
        }
        int x = position.getX();
        int y = position.getY();
        JLabel playerCell = cells[y][x];
        playerCell.setText("P");
        playerCell.setBackground(new Color(210, 235, 255));
    }
}
