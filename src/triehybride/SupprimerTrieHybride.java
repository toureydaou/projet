package triehybride;

    
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SupprimerTrieHybride {
        public static void suppression(String cheminFichier) {
    
            try {
                // instanciation d'un arbre patrica via le fichier pat.json
                TrieHybride trie = new TrieHybride();
                trie.importJson("trie.json");
    
                // suppression des mots contenus dans le fichier de l'arbre
    
                BufferedReader reader = new BufferedReader(new FileReader(new File(cheminFichier)));
                String mot = reader.readLine();
                long tempsTotal = 0;
                while (mot != null) {
                    long start = System.nanoTime();
                    trie.Suppression(trie.getRacine(), mot);
                    long fin = System.nanoTime();
                    tempsTotal += fin - start;
                    mot = reader.readLine();
                }
                reader.close();
                System.out.println("Temps suppression : " + tempsTotal + "nanosecondes");
    
                // écriture dans le fichier pat.json
    
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