package triehybride;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class RechercheMotPTrieHybride {
    public static void recherche(String file, String mot) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            TrieHybride trie = new TrieHybride();
            trie.importJson(file);
            System.out.println(mot);

            // nombre de mot ayant pour préfixe le chaîne spécifiée
            long start = System.nanoTime();
            boolean trouve = trie.Recherche(trie.getRacine(), mot);
            long fin = System.nanoTime();
            System.out.println("Temps de recherche : " + (fin - start));

            // écriture des mots dans le fichier mot.txt
            File fichierMots = new File("recherche.txt");
            if (fichierMots.exists()) {
                fichierMots.delete();
                fichierMots.createNewFile();
            } else {
                fichierMots.createNewFile();
            }

            // écriture dans le ficher
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierMots));
            writer.write(trouve + "");
            writer.close();
            System.out.println(mot + " est présent dans l'arbre "
                    + file + " : " + trouve);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
