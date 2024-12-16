package patricia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ProfondeurMoyennePatricia {
    public static void profondeurMoyenne(String file) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            Gson gson = new GsonBuilder().registerTypeAdapter(Patricia.class, new PatricaJsonDeserializer()).create();
            String patriciaJson = new String(Files.readAllBytes(Paths.get(file)));
            Patricia arbrePatricia = gson.fromJson(patriciaJson, Patricia.class);

            int nombreMots = Patricia.profondeurMoyenne(arbrePatricia);

            // écriture des mots dans le fichier mot.txt
            File fichierProfondeur = new File("profondeur.txt");
            if (fichierProfondeur.exists()) {
                fichierProfondeur.delete();
                fichierProfondeur.createNewFile();
            } else {
                fichierProfondeur.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierProfondeur));

            writer.write(nombreMots + "\n");

            writer.close();

            System.out
                    .println("Profondeur moyenne de l'arbre sauvegardés dans le fichier "
                            + fichierProfondeur.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
