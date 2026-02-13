package memento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsAppGUI {
    private static final Color COLOR_BG = new Color(245, 242, 235);
    private static final Color COLOR_CARD = new Color(255, 255, 255);
    private static final Color COLOR_ACCENT = new Color(34, 120, 205);
    private static final Color COLOR_ACCENT_SOFT = new Color(230, 242, 255);
    private static final Color COLOR_TEXT = new Color(24, 26, 28);
    private static final Color COLOR_TEXT_MUTED = new Color(92, 96, 102);
    private static final Color COLOR_BORDER = new Color(224, 224, 224);
    private static final Color COLOR_SUCCESS = new Color(34, 135, 84);

    private final Settings settings;
    private final SettingsCaretaker caretaker;

    private final JLabel volumeValueLabel = new JLabel();
    private final JLabel brightnessValueLabel = new JLabel();
    private final JLabel summaryLabel = new JLabel();
    private final JLabel statusLabel = new JLabel();
    private final JTextArea logArea = new JTextArea(8, 40);
    private Timer statusTimer;

    public SettingsAppGUI() {
        settings = new Settings(50, 70, false);
        caretaker = new SettingsCaretaker();
    }

    public void show() {
        setLookAndFeel();
        JFrame frame = new JFrame("Gestionnaire de Parametres - Memento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(12, 12));

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(16, 16, 16, 16));
        root.setBackground(COLOR_BG);

        JLabel titleLabel = new JLabel("Gestionnaire de Parametres");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(COLOR_TEXT);

        JLabel subtitleLabel = new JLabel("Parametres de l'application - Memento");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setForeground(COLOR_TEXT_MUTED);

        JPanel header = new JPanel(new BorderLayout(4, 4));
        header.setOpaque(false);
        header.add(titleLabel, BorderLayout.NORTH);
        header.add(subtitleLabel, BorderLayout.SOUTH);

        JPanel formPanel = createCardPanel();
        formPanel.setOpaque(true);
        formPanel.setBackground(COLOR_CARD);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JSlider volumeSlider = new JSlider(0, 100, settings.getVolume());
        JSlider brightnessSlider = new JSlider(0, 100, settings.getBrightness());
        JCheckBox darkModeCheckBox = new JCheckBox("Mode sombre", settings.isDarkMode());
        darkModeCheckBox.setOpaque(false);
        darkModeCheckBox.setForeground(COLOR_TEXT);
        darkModeCheckBox.setFont(new Font("SansSerif", Font.PLAIN, 14));

        styleSlider(volumeSlider);
        styleSlider(brightnessSlider);

        volumeValueLabel.setText(String.valueOf(settings.getVolume()));
        brightnessValueLabel.setText(String.valueOf(settings.getBrightness()));
        styleValueLabel(volumeValueLabel);
        styleValueLabel(brightnessValueLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(sectionLabel("[VOL] Volume"), gbc);

        gbc.gridx = 1;
        formPanel.add(volumeSlider, gbc);

        gbc.gridx = 2;
        formPanel.add(volumeValueLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(sectionLabel("[SUN] Luminosite"), gbc);

        gbc.gridx = 1;
        formPanel.add(brightnessSlider, gbc);

        gbc.gridx = 2;
        formPanel.add(brightnessValueLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(darkModeCheckBox, gbc);

        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        logArea.setBackground(new Color(250, 250, 250));
        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setPreferredSize(new Dimension(420, 160));
        scrollPane.setBorder(new LineBorder(COLOR_BORDER, 1, true));

        summaryLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        summaryLabel.setForeground(COLOR_TEXT_MUTED);
        summaryLabel.setBorder(new EmptyBorder(6, 8, 0, 8));

        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        statusLabel.setForeground(COLOR_SUCCESS);
        statusLabel.setBorder(new EmptyBorder(0, 8, 0, 8));

        root.add(header, BorderLayout.NORTH);
        root.add(formPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout(8, 8));
        southPanel.setOpaque(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        JButton saveButton = new JButton("Sauvegarder");
        JButton restoreButton = new JButton("Restaurer");
        restoreButton.setEnabled(false);
        styleButton(saveButton);
        styleButton(restoreButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(restoreButton);

        southPanel.add(buttonPanel, BorderLayout.NORTH);

        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setOpaque(false);
        summaryPanel.add(summaryLabel, BorderLayout.CENTER);
        summaryPanel.add(statusLabel, BorderLayout.SOUTH);
        southPanel.add(summaryPanel, BorderLayout.CENTER);
        southPanel.add(scrollPane, BorderLayout.SOUTH);

        root.add(southPanel, BorderLayout.SOUTH);

        frame.add(root, BorderLayout.CENTER);

        ChangeListener sliderListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                settings.setVolume(volumeSlider.getValue());
                settings.setBrightness(brightnessSlider.getValue());
                volumeValueLabel.setText(String.valueOf(settings.getVolume()));
                brightnessValueLabel.setText(String.valueOf(settings.getBrightness()));
                logState("Parametres modifies");
            }
        };

        volumeSlider.addChangeListener(sliderListener);
        brightnessSlider.addChangeListener(sliderListener);

        darkModeCheckBox.addActionListener(event -> {
            settings.setDarkMode(darkModeCheckBox.isSelected());
            logState("Mode sombre change");
        });

        saveButton.addActionListener(event -> {
            caretaker.save(settings);
            restoreButton.setEnabled(caretaker.hasSavedState());
            logState("Etat sauvegarde");
        });

        restoreButton.addActionListener(event -> {
            caretaker.restore(settings);
            volumeSlider.setValue(settings.getVolume());
            brightnessSlider.setValue(settings.getBrightness());
            darkModeCheckBox.setSelected(settings.isDarkMode());
            logState("Etat restaure");
        });

        logState("Etat initial");

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void logState(String action) {
        logArea.append(action + " : " + settings.describe() + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
        summaryLabel.setText(settings.describe());
        showStatus(action + " !");
    }

    private void showStatus(String message) {
        statusLabel.setText(message);
        if (statusTimer != null && statusTimer.isRunning()) {
            statusTimer.stop();
        }
        statusTimer = new Timer(1500, event -> statusLabel.setText(""));
        statusTimer.setRepeats(false);
        statusTimer.start();
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Keep default look and feel if system LAF fails.
        }
    }

    private JPanel createCardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COLOR_CARD);
        panel.setBorder(new LineBorder(COLOR_BORDER, 1, true));
        return panel;
    }

    private JLabel sectionLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(COLOR_TEXT);
        return label;
    }

    private void styleSlider(JSlider slider) {
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
        slider.setOpaque(false);
    }

    private void styleValueLabel(JLabel label) {
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setOpaque(true);
        label.setBackground(COLOR_ACCENT_SOFT);
        label.setForeground(COLOR_ACCENT);
        label.setBorder(new EmptyBorder(4, 10, 4, 10));
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(COLOR_ACCENT);
        button.setForeground(Color.WHITE);
        button.setBorder(new EmptyBorder(10, 22, 10, 22));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 40));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SettingsAppGUI().show());
    }
}
