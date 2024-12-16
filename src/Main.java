import patricia.ComptageMotsPatricia;
import patricia.ComptageNilPatricia;
import patricia.FusionPatricia;
import patricia.HauteurPatricia;
import patricia.InsererPatricia;
import patricia.InsertInPatriciaFromFile;
import patricia.ListeMotsPatricia;
import patricia.PrefixePatricia;
import patricia.ProfondeurMoyennePatricia;
import patricia.RechercheMotPatricia;
import patricia.SupprimerPatricia;

public class Main {
    public static void main(String[] args) {

        if (args.length > 1) {
            String fichier = args[1];
            switch (args[0]) {
                case "inserer":
                    InsererPatricia.insertion(fichier);
                    break;
                case "insererFichier":
                    InsertInPatriciaFromFile.insertInPatriciaFromFile(fichier);
                    break;
                case "suppression":
                    SupprimerPatricia.suppression(fichier);
                    break;
                case "listeMots":
                    ListeMotsPatricia.listeMots(fichier);
                    break;
                case "profondeurMoyenne":
                    ProfondeurMoyennePatricia.profondeurMoyenne(fichier);
                    break;
                case "prefixe":
                    String prefixe = args[2];
                    PrefixePatricia.prefixe(fichier, prefixe);
                    break;
                case "recherche":
                    String mot = args[2];
                    RechercheMotPatricia.recherche(fichier, mot);
                    break;
                case "hauteur":
                    HauteurPatricia.hauteur(fichier);
                    break;
                case "comptageNil":
                    ComptageNilPatricia.comptageNil(fichier);
                    break;
                case "comptageMots":
                    ComptageMotsPatricia.comptageMots(fichier);
                    break;
                case "fusion":
                    String fichierArbre2 = args[2];
                    FusionPatricia.fusion(fichier, fichierArbre2);
                    break;
                default:
                    break;
            }
        }

    }

}
