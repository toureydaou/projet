package triehybride;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ProfondeurMoyenneTrieHybride {
    public static void profondeurMoyenne(String file) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            TrieHybride trie = new TrieHybride();
            trie.importJson(file);
            float nombreMots = trie.ProfondeurMoyenne(trie.getRacine());

            // écriture des mots dans le fichier mot.txt
            File fichierProfondeur = new File("profondeur.txt");
            if (fichierProfondeur.exists()) {
                fichierProfondeur.delete();
                fichierProfondeur.createNewFile();
            } else {
                fichierProfondeur.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierProfondeur));

            writer.write(nombreMots + "");

            writer.close();

            System.out.println("Profondeur moyenne de l'arbre sauvegardés dans le fichier " + fichierProfondeur.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
