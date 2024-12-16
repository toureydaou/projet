package patricia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RechercheMotPatricia {
    public static void recherche(String file, String mot) {
        try {
            // instanciation d'un arbre patrica via le fichier json
            Gson gson = new GsonBuilder().registerTypeAdapter(Patricia.class, new PatricaJsonDeserializer()).create();
            String patriciaJson = new String(Files.readAllBytes(Paths.get(file)));
            Patricia arbrePatricia = gson.fromJson(patriciaJson, Patricia.class);

            System.out.println(mot);

            // nombre de mot ayant pour préfixe le chaîne spécifiée
            long start = System.nanoTime();
            boolean trouve = Patricia.recherche(arbrePatricia, mot);
            long fin = System.nanoTime();
            System.out.println("Temps de recherche : " + (fin - start));

            // écriture des mots dans le fichier mot.txt
            File fichierMots = new File("recherche.txt");
            if (fichierMots.exists()) {
                fichierMots.delete();
                fichierMots.createNewFile();
            } else {
                fichierMots.createNewFile();
            }

            // écriture dans le ficher
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierMots));
            writer.write(trouve + "\n");
            writer.close();
            System.out.println(mot + " est présent dans l'arbre "
                    + file + " : " + trouve);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
