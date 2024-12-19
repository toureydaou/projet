package triehybride;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ListeMotsTrieHybride {
    public static void listeMots(String file) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            TrieHybride trie = new TrieHybride();
            trie.importJson(file);
            // récupération de la liste des mots de l'arbre patricia
            
            List<String> mots = trie.ListeMots(trie.getRacine());

            // écriture des mots dans le fichier mot.txt
            File fichierMotsPatricia = new File("mot.txt");
            if (fichierMotsPatricia.exists()) {
                fichierMotsPatricia.delete();
                fichierMotsPatricia.createNewFile();
            } else {
                fichierMotsPatricia.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierMotsPatricia));
            for (String mot : mots) {
                writer.write(mot + "\n");
            }

            writer.close();

            System.out
                    .println("Liste des mots de l'arbre sauvegardés dans le fichier " + fichierMotsPatricia.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
