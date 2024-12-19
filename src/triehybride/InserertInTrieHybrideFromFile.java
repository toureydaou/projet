package triehybride;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class InserertInTrieHybrideFromFile {
    public static void insertInPatriciaFromFile(String cheminFichier) {
        try {
            // instanciation d'un arbre patrica via le fichier pat.json
            TrieHybride trie = new TrieHybride();

            long start = System.nanoTime();
            // trie.insererTexte(cheminFichier);
            long fin = System.nanoTime();
            System.out.println("Temps d'insertion : " + (fin - start));
            
            File fichierJsonPatricia = new File("trie.json");
            if (fichierJsonPatricia.exists()) {
                fichierJsonPatricia.delete();
                // fichierJsonPatricia.createNewFile();
            } else {
                fichierJsonPatricia.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierJsonPatricia));
            writer.write(trie.toString());
            writer.close();
            System.out.println("Arbre sauvegardé dans le fichier " +
                    fichierJsonPatricia.getPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
