import java.util.*;

public class GestionTaches {
    private final List<Tache> taches = new ArrayList<>();

    public void ajouterTache(Tache tache) {
        taches.add(tache);
        System.out.println("Tâche ajoutée : " + tache);
    }

    public void supprimerTache(Tache tache) {
        taches.remove(tache);
        System.out.println("Tâche supprimée : " + tache);
    }

    public Tache trouverTache(String description) {
        for (Tache t : taches) {
            if (t.getDescription().equals(description)) {
                return t;
            }
        }
        return null;
    }

    public void modifierTache(String ancienneDescription, String nouvelleDescription) {
        Tache t = trouverTache(ancienneDescription);
        if (t != null) {
            t.setDescription(nouvelleDescription);
            System.out.println("Tâche modifiée : " + t);
        } else {
            System.out.println("Tâche non trouvée : " + ancienneDescription);
        }
    }

    public List<Tache> getTaches() {
        return taches;
    }
}
