public class ApplicationGestionTaches {
    final GestionTaches gestionTaches;
    private final GestionnaireCommandes gestionnaireCommandes;

    public ApplicationGestionTaches() {
        this.gestionTaches = new GestionTaches();
        this.gestionnaireCommandes = new GestionnaireCommandes();
    }

    public void creerTache(String description) {
        Commande c = new CreerTacheCommande(gestionTaches, description);
        gestionnaireCommandes.executerCommande(c);
    }

    public void modifierTache(String ancienneDescription, String nouvelleDescription) {
        Commande c = new ModifierTacheCommande(gestionTaches, ancienneDescription, nouvelleDescription);
        gestionnaireCommandes.executerCommande(c);
    }

    public void supprimerTache(String description) {
        Commande c = new SupprimerTacheCommande(gestionTaches, description);
        gestionnaireCommandes.executerCommande(c);
    }

    public void annulerDerniereCommande() {
        gestionnaireCommandes.annulerDerniereCommande();
    }

    public void refaireDerniereCommande() {
        gestionnaireCommandes.refaireDerniereCommande();
    }

    public void afficherTaches() {
        System.out.println("\nListe des tâches :");
        for (Tache t : gestionTaches.getTaches()) {
            System.out.println("- " + t);
        }
    }
}
