package patricia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HauteurPatricia {
    public static void hauteur(String file) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            Gson gson = new GsonBuilder().registerTypeAdapter(Patricia.class, new PatricaJsonDeserializer()).create();
            String patriciaJson = new String(Files.readAllBytes(Paths.get(file)));
            Patricia arbrePatricia = gson.fromJson(patriciaJson, Patricia.class);

            int hauteur = Patricia.hauteur(arbrePatricia);

            // écriture de la hauteur dans le fichier hauteur.txt
            File fichierHauteur = new File("hauteur.txt");
            if (fichierHauteur.exists()) {
                fichierHauteur.delete();
                fichierHauteur.createNewFile();
            } else {
                fichierHauteur.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierHauteur));

            writer.write(hauteur + "\n");

            writer.close();

            System.out
                    .println("Profondeur hauteur l'arbre sauvegardée dans le fichier "
                            + fichierHauteur.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
