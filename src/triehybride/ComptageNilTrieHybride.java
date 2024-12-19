package triehybride;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ComptageNilTrieHybride {

    public static void comptageNils(String file) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            TrieHybride trie = new TrieHybride();
            trie.importJson(file);
            long start = System.nanoTime();
            int nombreMots = trie.ComptageNil(trie.getRacine());
            long fin = System.nanoTime();
            System.out.println("Temps de comptage des noeuds nils : " + (fin - start));

            // écriture de la hauteur dans le fichier nombreNil.txt
            File fichierNombreMots = new File("nombreNil.txt");
            if (fichierNombreMots.exists()) {
                fichierNombreMots.delete();
                fichierNombreMots.createNewFile();
            } else {
                fichierNombreMots.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierNombreMots));

            writer.write(nombreMots + "");

            writer.close();

            System.out.println("Nombre de pointeur vers Nil l'arbre sauvegardé dans le fichier " + fichierNombreMots.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
