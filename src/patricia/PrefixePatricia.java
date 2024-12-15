package patricia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PrefixePatricia {
    public static void prefixe(String file, String prefixe) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            Gson gson = new GsonBuilder().registerTypeAdapter(Patricia.class, new PatricaJsonDeserializer()).create();
            String patriciaJson = new String(Files.readAllBytes(Paths.get(file)));
            Patricia arbrePatricia = gson.fromJson(patriciaJson, Patricia.class);

            // nombre de mot ayant pour préfixe le chaîne spécifiée
            int nombreMots = Patricia.prefixe(arbrePatricia, prefixe);

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
            writer.write(nombreMots + "\n");
            writer.close();
            System.out.println("Nombre de mots dont " + prefixe + " est préfixe " + "sauvegardé dans le fichier "
                    + fichierMots.getPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
