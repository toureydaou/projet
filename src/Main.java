
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

public class Main {
    public static void main(String[] args) {

        Patricia patricia_vide = new Patricia();

        patricia_vide.setPrefixe("");
        patricia_vide.setFinDeMot(null);

        // TreeMap<String, Patricia> noeud_vide = new TreeMap<String, Patricia>();

        // Patricia.insertWord(patricia_vide, "carte");
        // Patricia.insertWord(patricia_vide, "carti");
        Patricia.insertWord(patricia_vide, "cart");
        Patricia.insertWord(patricia_vide, "car");
        // Patricia.insertWord(patricia_vide, "cai");
        Patricia.insertWord(patricia_vide, "dog");
        // Patricia.insertWord(patricia_vide, "bat");
        // Patricia.insertWord(patricia_vide, "ca");

        // System.out.println(Patricia.prefixe(patricia_vide, "ca"));
        // System.out.println(patricia_vide.toString());

        // System.out.println("---------------");

        Gson gson = new GsonBuilder().registerTypeAdapter(Patricia.class, new PatricaJsonDeserializer())
                .create();

        Patricia testArbre = gson.fromJson(patricia_vide.toString(), Patricia.class);

        System.out.println(testArbre.toString());
        // System.out.println(Patricia.supprimer(patricia_vide, "carti"));
        // System.out.println(Patricia.supprimer(patricia_vide, "caru"));
        // System.out.println(Patricia.supprimer(patricia_vide, "ca"));

        // String exampleBase = "A quel genial professeur de dactylographie sommes nous
        // redevables de la superbe phrase ci dessous, un modele du genre, que toute
        // dactylo connait par coeur puisque elle fait appel a chacune des touches du
        // clavier de la machine a ecrire ?";

        // for (String mot : Arrays.stream((exampleBase.split("
        // "))).collect(Collectors.toSet())) {
        // mot = mot.trim();
        // Patricia.insertWord(patricia_vide, mot);
        // }

        // System.out.println(Arrays.stream((exampleBase.split(""))).collect(Collectors.toSet()).size());
        // System.out.println(Patricia.comptageMots(patricia_vide));
        // System.out.println(Patricia.listeMots(patricia_vide).toString());
        // System.out.println(Patricia.listeMots(patricia_vide).size());
        // System.out.println("hauteur :" + Patricia.hauteur(patricia_vide));

        // System.out.println(Patricia.prefixe(patricia_vide, "dactylo"));

        // System.out.println(Patricia.supprimer(patricia_vide, "dactylo"));
        // System.out.println(patricia_vide);
        /*
         * test insertion de l'oeuvre de shakespeare dans un patricia tree
         * 
         */

        // String directoryPath =
        // "/home/kaiser/Sorbonne/M1_2024_2025/ALGAV/projet/Shakespeare";
        // ArrayList<String> mots = new ArrayList<>();
        // File dossier = new File(directoryPath);
        // String mot;
        // for (File fichier : dossier.listFiles()) {
        // try {
        // BufferedReader reader = new BufferedReader(new FileReader(fichier));
        // while ((mot = reader.readLine()) != null)
        // Patricia.insertWord(patricia_vide, mot);

        // reader.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // System.out.println(patricia_vide);
        // System.out.println(Patricia.recherche(patricia_vide, "zounds"));
        // System.out.println(Patricia.comptageMots(patricia_vide));
        // System.out.println(Patricia.listeMots(patricia_vide).toString());
        // System.out.println("hauteur :" + Patricia.hauteur(patricia_vide));
        // System.out.println(Patricia.profondeurMoyenne(patricia_vide));

    }

}
