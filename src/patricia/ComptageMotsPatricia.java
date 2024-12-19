package patricia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ComptageMotsPatricia {
    public static void comptageMots(String file) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            Gson gson = new GsonBuilder().registerTypeAdapter(Patricia.class, new PatricaJsonDeserializer()).create();
            String patriciaJson = new String(Files.readAllBytes(Paths.get(file)));
            Patricia arbrePatricia = gson.fromJson(patriciaJson, Patricia.class);

            long start = System.nanoTime();
            int nombreMots = Patricia.comptageMots(arbrePatricia);
            long fin = System.nanoTime();
            System.out.println("Temps de comptage des mots : " + (fin - start));

            // écriture de la hauteur dans le fichier nombreNil.txt
            File fichierNombreMots = new File("nombreMots.txt");
            if (fichierNombreMots.exists()) {
                fichierNombreMots.delete();
                fichierNombreMots.createNewFile();
            } else {
                fichierNombreMots.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierNombreMots));

            writer.write(nombreMots + "");

            writer.close();

            System.out
                    .println("Nombre de mots dans l'arbre sauvegardé dans le fichier "
                            + fichierNombreMots.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
