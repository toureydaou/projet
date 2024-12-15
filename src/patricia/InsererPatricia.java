package patricia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class InsererPatricia {
    public static void insertion(String cheminFichier) {
        File fichier = new File(cheminFichier);
        Patricia racine = new Patricia("", "");

        // lecture des mots du fichier et insertion dans l'arbre patricia
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fichier));
            String mot = reader.readLine();
            while (mot != null) {
                Patricia.insertWord(racine, mot);
                mot = reader.readLine();
            }
            reader.close();
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
