package triehybride;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class PrefixeTrieHybride {
    public static void prefixe(String file, String prefixe) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            TrieHybride trie = new TrieHybride();
            trie.importJson(file);
            // nombre de mot ayant pour préfixe le chaîne spécifiée
            long start = System.nanoTime();
            int nombreMots = trie.Prefixe(trie.getRacine(), prefixe);
            long fin = System.nanoTime();
            System.out.println("Temps d'execution : " + (fin - start) + " nanosecondes");

            // écriture des mots dans le fichier mot.txt
            File fichierMots = new File("prefixe.txt");
            if (fichierMots.exists()) {
                fichierMots.delete();
                fichierMots.createNewFile();
            } else {
                fichierMots.createNewFile();
            }

            // écriture dans le ficher
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierMots));
            writer.write(nombreMots + "");
            writer.close();
            System.out.println("Nombre de mots dont " + prefixe + " est préfixe " + "sauvegardé dans le fichier "
                    + fichierMots.getPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
