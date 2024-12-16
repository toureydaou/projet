package patricia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class InsererPatricia {
    public static void insertion(String cheminFichierOuDossier) {
        File fileOrDirectory = new File(cheminFichierOuDossier);
        Patricia racine = new Patricia("", "");

        // insere les mots dans l'arbre patricia depuis un dossier ou un fichier
        try {

            if (fileOrDirectory.isFile()) {
                BufferedReader reader = new BufferedReader(new FileReader(cheminFichierOuDossier));
                String mot = reader.readLine();
                while (mot != null) {
                    Patricia.insertWord(racine, mot);
                    mot = reader.readLine();
                }
                reader.close();
            } else {
                String mot;
                long tempsTotal = 0;
                for (File fichier : fileOrDirectory.listFiles()) {
                    BufferedReader reader = new BufferedReader(new FileReader(fichier));
                    mot = reader.readLine();
                    while (mot != null) {
                        long start = System.currentTimeMillis();
                        Patricia.insertWord(racine, mot);
                        long fin = System.currentTimeMillis();
                        mot = reader.readLine();
                        tempsTotal += fin - start;
                    }
                    reader.close();
                }
                System.out.println("Temps d'execution : " + tempsTotal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // écriture dans le fichier pat.json
        try {
            File fichierJsonPatricia = new File("pat.json");
            // si le fichier existe déja on le supprime
            if (fichierJsonPatricia.exists()) {
                fichierJsonPatricia.delete();
                // fichierJsonPatricia.createNewFile();
            } else {
                fichierJsonPatricia.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierJsonPatricia));
            writer.write(racine.toString());
            writer.close();
            System.out.println("Arbre sauvegardé dans le fichier " + fichierJsonPatricia.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
