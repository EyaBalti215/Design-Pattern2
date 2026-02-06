public class ModifierTacheCommande implements Commande {
    private final GestionTaches gestionTaches;
    private final String ancienneDescription;
    private final String nouvelleDescription;
    private String sauvegardeDescription;

    public ModifierTacheCommande(GestionTaches gestionTaches, String ancienneDescription, String nouvelleDescription) {
        this.gestionTaches = gestionTaches;
        this.ancienneDescription = ancienneDescription;
        this.nouvelleDescription = nouvelleDescription;
    }

    @Override
    public void executer() {
        Tache t = gestionTaches.trouverTache(ancienneDescription);
        if (t != null) {
            sauvegardeDescription = t.getDescription();
            gestionTaches.modifierTache(ancienneDescription, nouvelleDescription);
        } else {
            System.out.println("Tâche à modifier non trouvée : " + ancienneDescription);
        }
    }

    @Override
    public void annuler() {
        Tache t = gestionTaches.trouverTache(nouvelleDescription);
        if (t != null && sauvegardeDescription != null) {
            gestionTaches.modifierTache(nouvelleDescription, sauvegardeDescription);
            System.out.println("Annulation de la modification de la tâche : " + t);
        }
    }
}
