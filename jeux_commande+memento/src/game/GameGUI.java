package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class GameGUI {
    private static final int GRID_SIZE = 5;

    private final Position position = new Position(GRID_SIZE, 2, 2);
    private final PositionCaretaker caretaker = new PositionCaretaker();

    private final GridPanel gridPanel = new GridPanel(GRID_SIZE);
    private final JTextArea logArea = new JTextArea(6, 24);
    private final JLabel statusLabel = new JLabel();
    private JButton undoButton;

    public void show() {
        setLookAndFeel();

        JFrame frame = new JFrame("Mini Jeu Command + Memento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(12, 12));

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(14, 14, 14, 14));
        root.setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("Mini Jeu de Deplacement");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(title, BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout(12, 12));
        center.setOpaque(false);
        center.add(gridPanel, BorderLayout.CENTER);
        center.add(createControlsPanel(), BorderLayout.EAST);

        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setOpaque(false);
        logPanel.setBorder(new EmptyBorder(8, 0, 0, 0));

        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(new LineBorder(new Color(210, 210, 210), 1, true));
        logPanel.add(scrollPane, BorderLayout.CENTER);

        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        statusLabel.setForeground(new Color(60, 100, 160));
        logPanel.add(statusLabel, BorderLayout.SOUTH);

        root.add(header, BorderLayout.NORTH);
        root.add(center, BorderLayout.CENTER);
        root.add(logPanel, BorderLayout.SOUTH);

        frame.add(root, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);

        bindKeys(root);
        refreshUI("Etat initial");

        frame.setVisible(true);
    }

    private JPanel createControlsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0, 8, 0, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton upButton = createButton("Up", () -> move(0, -1, "Up"));
        JButton downButton = createButton("Down", () -> move(0, 1, "Down"));
        JButton leftButton = createButton("Left", () -> move(-1, 0, "Left"));
        JButton rightButton = createButton("Right", () -> move(1, 0, "Right"));
        JButton saveButton = createButton("Save", this::savePosition);
        undoButton = new JButton("Undo");
        configureButton(undoButton);
        undoButton.addActionListener(event -> undoMove());

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(upButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(leftButton, gbc);

        gbc.gridx = 1;
        panel.add(saveButton, gbc);

        gbc.gridx = 2;
        panel.add(rightButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(downButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(undoButton, gbc);

        undoButton.setEnabled(false);

        return panel;
    }

    private JButton createButton(String label, Runnable action) {
        JButton button = new JButton(label);
        configureButton(button);
        button.addActionListener(event -> action.run());
        return button;
    }

    private void configureButton(JButton button) {
        button.setPreferredSize(new Dimension(90, 36));
        button.setFocusPainted(false);
    }

    private void move(int dx, int dy, String label) {
        caretaker.save(position);
        Command command = new MoveCommand(position, dx, dy);
        command.execute();
        refreshUI("Move " + label);
    }

    private void savePosition() {
        caretaker.save(position);
        refreshUI("Saved position");
    }

    private void undoMove() {
        PositionMemento memento = caretaker.popLast();
        if (memento == null) {
            refreshUI("No saved position");
            return;
        }
        position.restore(memento);
        refreshUI("Undo");
    }

    private void refreshUI(String action) {
        gridPanel.updateGrid(position);
        logArea.append(action + " -> (" + position.getX() + ", " + position.getY() + ")\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
        statusLabel.setText("History: " + caretaker.size());
        if (undoButton != null) {
            undoButton.setEnabled(caretaker.hasHistory());
        }
    }

    private void bindKeys(JComponent component) {
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("UP"), "moveUp");
        component.getActionMap().put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                move(0, -1, "Up");
            }
        });

        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        component.getActionMap().put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                move(0, 1, "Down");
            }
        });

        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        component.getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                move(-1, 0, "Left");
            }
        });

        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        component.getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                move(1, 0, "Right");
            }
        });

        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke('W'), "moveUpW");
        component.getActionMap().put("moveUpW", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                move(0, -1, "Up");
            }
        });

        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke('S'), "moveDownS");
        component.getActionMap().put("moveDownS", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                move(0, 1, "Down");
            }
        });

        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke('A'), "moveLeftA");
        component.getActionMap().put("moveLeftA", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                move(-1, 0, "Left");
            }
        });

        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(KeyStroke.getKeyStroke('D'), "moveRightD");
        component.getActionMap().put("moveRightD", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                move(1, 0, "Right");
            }
        });
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Keep default look and feel if system LAF fails.
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameGUI().show());
    }
}
