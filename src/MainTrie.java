import triehybride.ComptageMotTrieHybride;
import triehybride.ComptageNilTrieHybride;
import triehybride.HauteurTrieHybride;
import triehybride.InsererTrieHybride;
import triehybride.InserertInTrieHybrideFromFile;
import triehybride.ListeMotsTrieHybride;
import triehybride.PrefixeTrieHybride;
import triehybride.ProfondeurMoyenneTrieHybride;
import triehybride.RechercheMotPTrieHybride;
import triehybride.SupprimerTrieHybride;

public class MainTrie {
    public static void main(String[] args) {

        if (args.length > 1) {
            String fichier = args[1];
            switch (args[0]) {
                case "inserer":
                    InsererTrieHybride.insertion(fichier);
                    break;
                case "insererFichier":
                    InserertInTrieHybrideFromFile.insertInPatriciaFromFile(fichier);
                    break;
                case "suppression":
                    SupprimerTrieHybride.suppression(fichier);
                    break;
                case "listeMots":
                    ListeMotsTrieHybride.listeMots(fichier);
                    break;
                case "profondeurMoyenne":
                    ProfondeurMoyenneTrieHybride.profondeurMoyenne(fichier);
                    break;
                case "prefixe":
                    String prefixe = args[2];
                    PrefixeTrieHybride.prefixe(fichier, prefixe);
                    break;
                case "recherche":
                    String mot = args[2];
                    RechercheMotPTrieHybride.recherche(fichier, mot);
                    break;
                case "hauteur":
                    HauteurTrieHybride.hauteur(fichier);
                    break;
                case "comptageNil":
                    ComptageNilTrieHybride.comptageNils(fichier);
                    break;
                case "comptageMots":
                    ComptageMotTrieHybride.comptageMots(fichier);
                    break;
                default:
                    break;
            }
        }

    }
}
