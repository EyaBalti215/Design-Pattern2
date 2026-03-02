package flyweight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TDFlyweightGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Création du monde
            GameWorldFlyweight world = new GameWorldFlyweight();
            world.generateEnemies(10);

            // Création de la fenêtre
            JFrame frame = new JFrame("Jeu Flyweight avec boutons");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BorderLayout());

            // Panel de jeu
            GamePanel gamePanel = new GamePanel(world.getEnemies());
            frame.add(gamePanel, BorderLayout.CENTER);

            // Panel des boutons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // bien centré
            JButton addBtn = new JButton("Ajouter");
            JButton modifyBtn = new JButton("Modifier");
            JButton deleteBtn = new JButton("Supprimer");

            buttonPanel.add(addBtn);
            buttonPanel.add(modifyBtn);
            buttonPanel.add(deleteBtn);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // --- ACTIONS DES BOUTONS ---
            addBtn.addActionListener((ActionEvent e) -> {
                world.addRandomEnemy(gamePanel.getWidth(), gamePanel.getHeight());
                gamePanel.setEnemies(world.getEnemies());
            });

            modifyBtn.addActionListener((ActionEvent e) -> {
                world.modifyRandomEnemy();
                gamePanel.repaint();
            });

            deleteBtn.addActionListener((ActionEvent e) -> {
                world.removeLastEnemy();
                gamePanel.setEnemies(world.getEnemies());
            });

            // Timer pour déplacement automatique
            Timer timer = new Timer(30, e -> {
                gamePanel.updatePositions();
                gamePanel.repaint();
            });
            timer.start();

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
