import java.util.*;

public class GestionnaireCommandes {
    private final List<Commande> historique = new ArrayList<>();
    private int indexCourant = -1;

    public void executerCommande(Commande c) {
        
        while (historique.size() > indexCourant + 1) {
            historique.remove(historique.size() - 1);
        }
        c.executer();
        historique.add(c);
        indexCourant++;
    }

    public void annulerDerniereCommande() {
        if (indexCourant >= 0) {
            Commande c = historique.get(indexCourant);
            c.annuler();
            indexCourant--;
        } else {
            System.out.println("Aucune commande à annuler.");
        }
    }

    
    public void refaireDerniereCommande() {
        if (indexCourant + 1 < historique.size()) {
            Commande c = historique.get(indexCourant + 1);
            c.executer();
            indexCourant++;
        } else {
            System.out.println("Aucune commande à rétablir.");
        }
    }
}
