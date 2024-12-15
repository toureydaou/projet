
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import patricia.ComptageMotsPatricia;
import patricia.ComptageNilPatricia;
import patricia.FusionPatricia;
import patricia.HauteurPatricia;
import patricia.InsererPatricia;
import patricia.ListeMotsPatricia;
import patricia.PrefixePatricia;
import patricia.ProfondeurMoyennePatricia;
import patricia.SupprimerPatricia;

public class Main {
    public static void main(String[] args) {

        if (args.length > 1) {
            String fichier = args[1];
            switch (args[0]) {
                case "inserer":
                    InsererPatricia.insertion(fichier);
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
