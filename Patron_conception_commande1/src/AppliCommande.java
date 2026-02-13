public class AppliCommande {
    public static void main(String[] args) {
        ApplicationGestionTaches app = new ApplicationGestionTaches();

        System.out.println("--- Création de tâches ---");
        app.creerTache("Acheter du pain");
        app.creerTache("Faire les devoirs");
        app.creerTache("Appeler le médecin");
        app.afficherTaches();

        System.out.println("\n--- Modification d'une tâche ---");
        app.modifierTache("Acheter du pain", "Acheter du pain et du lait");
        app.afficherTaches();

        System.out.println("\n--- Suppression d'une tâche ---");
        app.supprimerTache("Faire les devoirs");
        app.afficherTaches();

        System.out.println("\n--- Annulation de la dernière commande (suppression) ---");
        app.annulerDerniereCommande();
        app.afficherTaches();

        System.out.println("\n--- Annulation de la modification ---");
        app.annulerDerniereCommande();
        app.afficherTaches();

        System.out.println("\n--- Annulation de la création ---");
        app.annulerDerniereCommande();
        app.afficherTaches();

        System.out.println("\n--- Rétablissement (redo) de la dernière commande ---");
        app.refaireDerniereCommande();
        app.afficherTaches();
    }
}
