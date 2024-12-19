package triehybride;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class HauteurTrieHybride {
    public static void hauteur(String file) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            TrieHybride trie = new TrieHybride();
            trie.importJson(file);
            long start = System.nanoTime();
            int hauteur = trie.Hauteur(trie.getRacine());
            long fin = System.nanoTime();
            System.out.println("Temps de calcul de la hauteur " + (fin - start));
            // écriture de la hauteur dans le fichier hauteur.txt
            File fichierHauteur = new File("hauteur.txt");
            if (fichierHauteur.exists()) {
                fichierHauteur.delete();
                fichierHauteur.createNewFile();
            } else {
                fichierHauteur.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierHauteur));

            writer.write(hauteur + "");

            writer.close();

            System.out.println("Hauteur de l'arbre sauvegardée dans le fichier " + fichierHauteur.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
