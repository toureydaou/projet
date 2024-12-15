package patricia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ComptageNilPatricia {
    public static void comptageNil(String file) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            Gson gson = new GsonBuilder().registerTypeAdapter(Patricia.class, new PatricaJsonDeserializer()).create();
            String patriciaJson = new String(Files.readAllBytes(Paths.get(file)));
            Patricia arbrePatricia = gson.fromJson(patriciaJson, Patricia.class);

            int nombreNil = Patricia.comptageNil(arbrePatricia);

            // écriture de la hauteur dans le fichier nombreNil.txt
            File fichierHauteur = new File("nombreNil.txt");
            if (fichierHauteur.exists()) {
                fichierHauteur.delete();
                fichierHauteur.createNewFile();
            } else {
                fichierHauteur.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierHauteur));

            writer.write(nombreNil + "\n");

            writer.close();

            System.out
                    .println("Nombre de pointeur vers Nil l'arbre sauvegardé dans le fichier "
                            + fichierHauteur.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
