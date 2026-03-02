package Patron_conception_specification_ex2.ui;

import Patron_conception_specification_ex2.model.Candidate;
import Patron_conception_specification_ex2.specification.*;
import Patron_conception_specification_ex2.service.CandidateFilterService;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

public class CandidateManagementApplication extends JFrame {
    private List<Candidate> candidates;
    private CandidateFilterService filterService;

    // Colors
    private static final Color PRIMARY = new Color(79, 70, 229);       // Indigo
    private static final Color PRIMARY_HOVER = new Color(67, 56, 202);
    private static final Color ACCENT = new Color(16, 185, 129);       // Emerald
    private static final Color ACCENT_HOVER = new Color(5, 150, 105);
    private static final Color DANGER = new Color(239, 68, 68);
    private static final Color BG_MAIN = new Color(241, 245, 249);     // Slate 100
    private static final Color BG_CARD = Color.WHITE;
    private static final Color BG_HEADER = new Color(30, 41, 59);      // Slate 800
    private static final Color TEXT_PRIMARY = new Color(15, 23, 42);    // Slate 900
    private static final Color TEXT_SECONDARY = new Color(100, 116, 139); // Slate 500
    private static final Color BORDER_COLOR = new Color(226, 232, 240); // Slate 200
    private static final Color ROW_EVEN = new Color(248, 250, 252);
    private static final Color ROW_ODD = Color.WHITE;
    private static final Color ROW_HOVER = new Color(238, 242, 255);   // Indigo 50
    private static final Color TABLE_HEADER_BG = new Color(241, 245, 249);

    // Fonts
    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 22);
    private static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FONT_LABEL = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_INPUT = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FONT_TABLE = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_TABLE_HEADER = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FONT_CHECKBOX = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONT_TAB = new Font("Segoe UI", Font.BOLD, 14);

    // UI Components
    private JTextField nameField;
    private JSpinner ageSpinner;
    private JSpinner gradeSpinner;
    private JSpinner experienceSpinner;
    private JCheckBox scholarshipCheckBox;

    private JCheckBox ageFilterCheckBox;
    private JCheckBox gradeFilterCheckBox;
    private JCheckBox experienceFilterCheckBox;
    private JCheckBox scholarshipFilterCheckBox;
    private JCheckBox advancedGradeFilterCheckBox;

    private JTable candidatesTable;
    private DefaultTableModel candidatesTableModel;
    private JTable resultTable;
    private DefaultTableModel resultTableModel;

    private JLabel statusLabel;
    private JLabel countLabel;

    public CandidateManagementApplication() {
        candidates = new ArrayList<>();
        filterService = new CandidateFilterService();

        setTitle("Candidate Management - Specification Pattern");
        setSize(1250, 750);
        setMinimumSize(new Dimension(900, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_MAIN);

        createUI();
    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_MAIN);

        // Header
        mainPanel.add(createHeader(), BorderLayout.NORTH);

        // Tabbed Pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(FONT_TAB);
        tabbedPane.setBackground(BG_MAIN);
        tabbedPane.setForeground(TEXT_PRIMARY);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        tabbedPane.addTab("  \u2795  Add Candidates  ", createAddCandidatePanel());
        tabbedPane.addTab("  \uD83D\uDD0D  Filter & Results  ", createFilterPanel());

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG_HEADER);
        header.setBorder(BorderFactory.createEmptyBorder(18, 30, 18, 30));

        JLabel title = new JLabel("\uD83C\uDFAF Candidate Management System");
        title.setFont(FONT_TITLE);
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.WEST);

        JLabel subtitle = new JLabel("Specification Design Pattern  ");
        subtitle.setFont(FONT_LABEL);
        subtitle.setForeground(new Color(148, 163, 184));
        header.add(subtitle, BorderLayout.EAST);

        return header;
    }

    private JPanel createAddCandidatePanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(BG_MAIN);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        // ---- Form Card ----
        JPanel formCard = createCard();
        formCard.setLayout(new BorderLayout(10, 15));
        formCard.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(12, BORDER_COLOR),
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));

        JLabel formTitle = new JLabel("\uD83D\uDCDD  Add New Candidate");
        formTitle.setFont(FONT_SUBTITLE);
        formTitle.setForeground(PRIMARY);
        formCard.add(formTitle, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        fieldsPanel.add(createLabel("Name"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        nameField = createStyledTextField(20);
        fieldsPanel.add(nameField, gbc);

        // Age
        gbc.gridx = 2; gbc.weightx = 0;
        fieldsPanel.add(createLabel("Age"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.5;
        ageSpinner = createStyledSpinner(new SpinnerNumberModel(20, 0, 100, 1));
        fieldsPanel.add(ageSpinner, gbc);

        // Grade
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        fieldsPanel.add(createLabel("Grade"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        gradeSpinner = createStyledSpinner(new SpinnerNumberModel(10.0, 0.0, 20.0, 0.5));
        fieldsPanel.add(gradeSpinner, gbc);

        // Experience
        gbc.gridx = 2; gbc.weightx = 0;
        fieldsPanel.add(createLabel("Experience (years)"), gbc);
        gbc.gridx = 3; gbc.weightx = 0.5;
        experienceSpinner = createStyledSpinner(new SpinnerNumberModel(1, 0, 50, 1));
        fieldsPanel.add(experienceSpinner, gbc);

        // Scholarship
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        fieldsPanel.add(createLabel("Scholarship"), gbc);
        gbc.gridx = 1;
        scholarshipCheckBox = new JCheckBox("Has Scholarship");
        scholarshipCheckBox.setFont(FONT_CHECKBOX);
        scholarshipCheckBox.setForeground(TEXT_PRIMARY);
        scholarshipCheckBox.setOpaque(false);
        scholarshipCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fieldsPanel.add(scholarshipCheckBox, gbc);

        formCard.add(fieldsPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        buttonsPanel.setOpaque(false);

        JButton clearButton = createStyledButton("Clear Form", DANGER, Color.WHITE);
        clearButton.addActionListener(e -> clearForm());
        buttonsPanel.add(clearButton);

        JButton addButton = createStyledButton("\u2795  Add Candidate", PRIMARY, Color.WHITE);
        addButton.addActionListener(e -> addCandidate());
        buttonsPanel.add(addButton);

        formCard.add(buttonsPanel, BorderLayout.SOUTH);

        // ---- Table Card ----
        JPanel tableCard = createCard();
        tableCard.setLayout(new BorderLayout(10, 10));
        tableCard.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(12, BORDER_COLOR),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JPanel tableHeader = new JPanel(new BorderLayout());
        tableHeader.setOpaque(false);
        JLabel tableTitle = new JLabel("\uD83D\uDCCB  All Candidates");
        tableTitle.setFont(FONT_SUBTITLE);
        tableTitle.setForeground(PRIMARY);
        tableHeader.add(tableTitle, BorderLayout.WEST);

        countLabel = new JLabel("0 candidates");
        countLabel.setFont(FONT_LABEL);
        countLabel.setForeground(TEXT_SECONDARY);
        tableHeader.add(countLabel, BorderLayout.EAST);

        tableCard.add(tableHeader, BorderLayout.NORTH);

        candidatesTableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        candidatesTableModel.addColumn("Name");
        candidatesTableModel.addColumn("Age");
        candidatesTableModel.addColumn("Grade");
        candidatesTableModel.addColumn("Experience");
        candidatesTableModel.addColumn("Scholarship");

        candidatesTable = createStyledTable(candidatesTableModel);
        JScrollPane scrollPane = new JScrollPane(candidatesTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        scrollPane.getViewport().setBackground(Color.WHITE);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        panel.add(formCard, BorderLayout.NORTH);
        panel.add(tableCard, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createFilterPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(BG_MAIN);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        // ---- Criteria Card ----
        JPanel criteriaCard = createCard();
        criteriaCard.setLayout(new BorderLayout(10, 15));
        criteriaCard.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(12, BORDER_COLOR),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));
        criteriaCard.setPreferredSize(new Dimension(280, 0));

        JLabel criteriaTitle = new JLabel("\u2699\uFE0F  Filter Criteria");
        criteriaTitle.setFont(FONT_SUBTITLE);
        criteriaTitle.setForeground(PRIMARY);
        criteriaCard.add(criteriaTitle, BorderLayout.NORTH);

        JPanel checksPanel = new JPanel();
        checksPanel.setLayout(new BoxLayout(checksPanel, BoxLayout.Y_AXIS));
        checksPanel.setOpaque(false);

        ageFilterCheckBox = createStyledCheckBox("\uD83D\uDC64  Age \u2265 18");
        gradeFilterCheckBox = createStyledCheckBox("\uD83D\uDCCA  Grade \u2265 12");
        experienceFilterCheckBox = createStyledCheckBox("\uD83D\uDCBC  Experience \u2265 2 years");
        scholarshipFilterCheckBox = createStyledCheckBox("\uD83C\uDF93  Has Scholarship");
        advancedGradeFilterCheckBox = createStyledCheckBox("\u2B50  Advanced Grade \u2265 14");

        checksPanel.add(Box.createVerticalStrut(10));
        checksPanel.add(ageFilterCheckBox);
        checksPanel.add(Box.createVerticalStrut(12));
        checksPanel.add(gradeFilterCheckBox);
        checksPanel.add(Box.createVerticalStrut(12));
        checksPanel.add(experienceFilterCheckBox);
        checksPanel.add(Box.createVerticalStrut(12));
        checksPanel.add(scholarshipFilterCheckBox);
        checksPanel.add(Box.createVerticalStrut(12));
        checksPanel.add(advancedGradeFilterCheckBox);
        checksPanel.add(Box.createVerticalGlue());

        criteriaCard.add(checksPanel, BorderLayout.CENTER);

        JButton filterButton = createStyledButton("\uD83D\uDD0D  Apply Filters", ACCENT, Color.WHITE);
        filterButton.addActionListener(e -> applyFilters());
        JPanel filterBtnPanel = new JPanel(new BorderLayout());
        filterBtnPanel.setOpaque(false);
        filterBtnPanel.add(filterButton, BorderLayout.CENTER);
        criteriaCard.add(filterBtnPanel, BorderLayout.SOUTH);

        // ---- Result Card ----
        JPanel resultCard = createCard();
        resultCard.setLayout(new BorderLayout(10, 10));
        resultCard.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(12, BORDER_COLOR),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JPanel resultHeader = new JPanel(new BorderLayout());
        resultHeader.setOpaque(false);
        JLabel resultTitle = new JLabel("\u2705  Eligible Candidates");
        resultTitle.setFont(FONT_SUBTITLE);
        resultTitle.setForeground(ACCENT);
        resultHeader.add(resultTitle, BorderLayout.WEST);

        statusLabel = new JLabel("No filter applied");
        statusLabel.setFont(FONT_LABEL);
        statusLabel.setForeground(TEXT_SECONDARY);
        resultHeader.add(statusLabel, BorderLayout.EAST);

        resultCard.add(resultHeader, BorderLayout.NORTH);

        resultTableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        resultTableModel.addColumn("Name");
        resultTableModel.addColumn("Age");
        resultTableModel.addColumn("Grade");
        resultTableModel.addColumn("Experience");
        resultTableModel.addColumn("Scholarship");

        resultTable = createStyledTable(resultTableModel);
        JScrollPane resultScroll = new JScrollPane(resultTable);
        resultScroll.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        resultScroll.getViewport().setBackground(Color.WHITE);
        resultCard.add(resultScroll, BorderLayout.CENTER);

        panel.add(criteriaCard, BorderLayout.WEST);
        panel.add(resultCard, BorderLayout.CENTER);

        return panel;
    }

    // ===================== Helper Methods =====================

    private JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(BG_CARD);
        return card;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text + ":");
        label.setFont(FONT_LABEL);
        label.setForeground(TEXT_SECONDARY);
        return label;
    }

    private JTextField createStyledTextField(int cols) {
        JTextField field = new JTextField(cols);
        field.setFont(FONT_INPUT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        field.setBackground(Color.WHITE);
        field.setCaretColor(PRIMARY);
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(PRIMARY, 2, true),
                        BorderFactory.createEmptyBorder(7, 11, 7, 11)
                ));
            }
            public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
        });
        return field;
    }

    private JSpinner createStyledSpinner(SpinnerNumberModel model) {
        JSpinner spinner = new JSpinner(model);
        spinner.setFont(FONT_INPUT);
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
            tf.setFont(FONT_INPUT);
            tf.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        }
        spinner.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1, true));
        return spinner;
    }

    private JButton createStyledButton(String text, Color bg, Color fg) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(bg.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(bg.brighter().equals(Color.WHITE) ? bg : bg.darker());
                } else {
                    g2.setColor(bg);
                }
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(FONT_BUTTON);
        button.setForeground(fg);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 24, 10, 24));
        return button;
    }

    private JCheckBox createStyledCheckBox(String text) {
        JCheckBox cb = new JCheckBox(text);
        cb.setFont(FONT_CHECKBOX);
        cb.setForeground(TEXT_PRIMARY);
        cb.setOpaque(false);
        cb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cb.setAlignmentX(Component.LEFT_ALIGNMENT);
        return cb;
    }

    private JTable createStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? ROW_EVEN : ROW_ODD);
                } else {
                    c.setBackground(new Color(199, 210, 254)); // Indigo 200
                    c.setForeground(TEXT_PRIMARY);
                }
                return c;
            }
        };
        table.setFont(FONT_TABLE);
        table.setRowHeight(38);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setGridColor(BORDER_COLOR);
        table.setSelectionBackground(new Color(199, 210, 254));
        table.setSelectionForeground(TEXT_PRIMARY);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFillsViewportHeight(true);

        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setFont(FONT_TABLE_HEADER);
        header.setBackground(TABLE_HEADER_BG);
        header.setForeground(TEXT_PRIMARY);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, PRIMARY));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 42));
        header.setReorderingAllowed(false);

        // Center align columns except first
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        return table;
    }

    // ===================== Actions =====================

    private void addCandidate() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a name!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int age = (Integer) ageSpinner.getValue();
        double grade = (Double) gradeSpinner.getValue();
        int experience = (Integer) experienceSpinner.getValue();
        boolean hasScholarship = scholarshipCheckBox.isSelected();

        Candidate candidate = new Candidate(name, age, grade, experience, hasScholarship);
        candidates.add(candidate);

        updateCandidatesTable();
        clearForm();

        JOptionPane.showMessageDialog(this,
                "Candidate \"" + name + "\" added successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearForm() {
        nameField.setText("");
        ageSpinner.setValue(20);
        gradeSpinner.setValue(10.0);
        experienceSpinner.setValue(1);
        scholarshipCheckBox.setSelected(false);
        nameField.requestFocus();
    }

    private void updateCandidatesTable() {
        candidatesTableModel.setRowCount(0);
        for (Candidate candidate : candidates) {
            Object[] row = {
                    candidate.getName(),
                    candidate.getAge(),
                    candidate.getGrade(),
                    candidate.getExperienceYears(),
                    candidate.isHasScholarship() ? "\u2705 Yes" : "\u274C No"
            };
            candidatesTableModel.addRow(row);
        }
        countLabel.setText(candidates.size() + " candidate" + (candidates.size() != 1 ? "s" : ""));
    }

    private void applyFilters() {
        if (candidates.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No candidates available. Please add candidates first.",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Specification spec = null;

        if (ageFilterCheckBox.isSelected()) {
            spec = new AgeSpecification(18);
        }
        if (gradeFilterCheckBox.isSelected()) {
            Specification gradeSpec = new GradeSpecification(12);
            spec = (spec == null) ? gradeSpec : spec.and(gradeSpec);
        }
        if (experienceFilterCheckBox.isSelected()) {
            Specification expSpec = new ExperienceSpecification(2);
            spec = (spec == null) ? expSpec : spec.and(expSpec);
        }
        if (scholarshipFilterCheckBox.isSelected()) {
            Specification schSpec = new ScholarshipSpecification();
            spec = (spec == null) ? schSpec : spec.and(schSpec);
        }
        if (advancedGradeFilterCheckBox.isSelected()) {
            Specification advGradeSpec = new AdvancedGradeSpecification(14);
            spec = (spec == null) ? advGradeSpec : spec.and(advGradeSpec);
        }

        if (spec == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select at least one criterion!",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        List<Candidate> filteredCandidates = filterService.filterCandidates(candidates, spec);

        resultTableModel.setRowCount(0);
        for (Candidate candidate : filteredCandidates) {
            Object[] row = {
                    candidate.getName(),
                    candidate.getAge(),
                    candidate.getGrade(),
                    candidate.getExperienceYears(),
                    candidate.isHasScholarship() ? "\u2705 Yes" : "\u274C No"
            };
            resultTableModel.addRow(row);
        }

        statusLabel.setText("Found " + filteredCandidates.size() + " / " + candidates.size() + " candidates");
        statusLabel.setForeground(filteredCandidates.isEmpty() ? DANGER : ACCENT);
    }

    // ===================== Custom Border =====================

    static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color color;

        RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius / 2, radius / 2, radius / 2, radius / 2);
        }
    }

    // ===================== Main =====================

    public static void main(String[] args) {
        // Set system look and feel enhancements
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                UIManager.put("TabbedPane.selected", BG_CARD);
                UIManager.put("TabbedPane.contentAreaColor", BG_MAIN);
            } catch (Exception ignored) {}

            CandidateManagementApplication app = new CandidateManagementApplication();
            app.setVisible(true);
        });
    }
}
