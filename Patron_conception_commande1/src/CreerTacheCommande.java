public class CreerTacheCommande implements Commande {
    private final GestionTaches gestionTaches;
    private final Tache tache;

    public CreerTacheCommande(GestionTaches gestionTaches, String description) {
        this.gestionTaches = gestionTaches;
        this.tache = new Tache(description);
    }

    @Override
    public void executer() {
        gestionTaches.ajouterTache(tache);
    }

    @Override
    public void annuler() {
        gestionTaches.supprimerTache(tache);
        System.out.println("Annulation de la création de la tâche : " + tache);
    }
}
