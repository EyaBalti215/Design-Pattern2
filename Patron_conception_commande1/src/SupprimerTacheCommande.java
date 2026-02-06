public class SupprimerTacheCommande implements Commande {
    private final GestionTaches gestionTaches;
    private final Tache tache;

    public SupprimerTacheCommande(GestionTaches gestionTaches, String description) {
        this.gestionTaches = gestionTaches;
        this.tache = gestionTaches.trouverTache(description);
    }

    @Override
    public void executer() {
        if (tache != null) {
            gestionTaches.supprimerTache(tache);
        } else {
            System.out.println("Tâche à supprimer non trouvée : " + (tache != null ? tache.getDescription() : ""));
        }
    }

    @Override
    public void annuler() {
        if (tache != null) {
            gestionTaches.ajouterTache(tache);
            System.out.println("Annulation de la suppression de la tâche : " + tache);
        }
    }
}
