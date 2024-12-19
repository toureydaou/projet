package triehybride;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ComptageMotTrieHybride {
    public static void comptageMots(String file) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            TrieHybride trie = new TrieHybride();
            trie.importJson(file);
            long start = System.nanoTime();
            int nombreMots = trie.ComptageMots(trie.getRacine());
            long fin = System.nanoTime();
            System.out.println("Temps de comptage des mots : " + (fin - start));

            // écriture de la hauteur dans le fichier nombreNil.txt
            File fichierNombreMots = new File("nombreMots.txt");
            if (fichierNombreMots.exists()) {
                fichierNombreMots.delete();
                fichierNombreMots.createNewFile();
            } else {
                fichierNombreMots.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierNombreMots));

            writer.write(nombreMots + "");

            writer.close();

            System.out
                    .println("Nombre de mots dans l'arbre sauvegardé dans le fichier "
                            + fichierNombreMots.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
