package patricia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ListeMotsPatricia {
    public static void listeMots(String file) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            Gson gson = new GsonBuilder().registerTypeAdapter(Patricia.class, new PatricaJsonDeserializer()).create();

            String patriciaJson = new String(Files.readAllBytes(Paths.get(file)));

            Patricia arbrePatricia = gson.fromJson(patriciaJson, Patricia.class);

            // récupération de la liste des mots de l'arbre patricia
            ArrayList<String> mots = Patricia.listeMots(arbrePatricia);

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
