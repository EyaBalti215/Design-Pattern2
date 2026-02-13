import javax.swing.*;
import java.awt.*;


public class FenetreGestionTaches extends JFrame {
    private final ApplicationGestionTaches app;
    private final DefaultListModel<String> modeleListe;
    private final JList<String> listeTaches;
    private final JTextField champDescription;
    private final JButton boutonCreer, boutonModifier, boutonSupprimer, boutonAnnuler, boutonRefaire;

    public FenetreGestionTaches() {
        super("Gestionnaire de Tâches (Patron Commande)");
        app = new ApplicationGestionTaches();
        modeleListe = new DefaultListModel<>();
        listeTaches = new JList<>(modeleListe);
        champDescription = new JTextField(20);
        boutonCreer = new JButton("Créer");
        boutonModifier = new JButton("Modifier");
        boutonSupprimer = new JButton("Supprimer");
        boutonAnnuler = new JButton("Annuler (Undo)");
        boutonRefaire = new JButton("Rétablir (Redo)");

        setLayout(new BorderLayout());
        JPanel panelHaut = new JPanel();
        panelHaut.add(new JLabel("Description : "));
        panelHaut.add(champDescription);
        panelHaut.add(boutonCreer);
        panelHaut.add(boutonModifier);
        panelHaut.add(boutonSupprimer);
        panelHaut.add(boutonAnnuler);
        panelHaut.add(boutonRefaire);
        add(panelHaut, BorderLayout.NORTH);
        add(new JScrollPane(listeTaches), BorderLayout.CENTER);

        boutonCreer.addActionListener(e -> {
            String desc = champDescription.getText().trim();
            if (!desc.isEmpty()) {
                app.creerTache(desc);
                majListe();
                champDescription.setText("");
            }
        });
        boutonModifier.addActionListener(e -> {
            String selected = listeTaches.getSelectedValue();
            String desc = champDescription.getText().trim();
            if (selected != null && !desc.isEmpty()) {
                String ancienne = selected.replaceAll(" \\[.*]$", "");
                app.modifierTache(ancienne, desc);
                majListe();
            }
        });
        boutonSupprimer.addActionListener(e -> {
            String selected = listeTaches.getSelectedValue();
            if (selected != null) {
                String desc = selected.replaceAll(" \\[.*]$", "");
                app.supprimerTache(desc);
                majListe();
            }
        });
        boutonAnnuler.addActionListener(e -> {
            app.annulerDerniereCommande();
            majListe();
        });
        boutonRefaire.addActionListener(e -> {
            app.refaireDerniereCommande();
            majListe();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 350);
        setLocationRelativeTo(null);
        majListe();
    }

    private void majListe() {
        modeleListe.clear();
        for (Tache t : app.gestionTaches.getTaches()) {
            modeleListe.addElement(t.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FenetreGestionTaches().setVisible(true));
    }
}
