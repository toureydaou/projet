package patricia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SupprimerPatricia {
    public static void suppression(String cheminFichier) {

        try {
            // instanciation d'un arbre patrica via le fichier pat.json
            Gson gson = new GsonBuilder().registerTypeAdapter(Patricia.class, new PatricaJsonDeserializer()).create();

            String patriciaJson = new String(Files.readAllBytes(Paths.get("pat.json")));

            Patricia arbrePatricia = gson.fromJson(patriciaJson, Patricia.class);

            // suppression des mots contenus dans le fichier de l'arbre

            BufferedReader reader = new BufferedReader(new FileReader(new File(cheminFichier)));
            String mot = reader.readLine();
            long tempsTotal = 0;
            while (mot != null) {
                long start = System.nanoTime();
                Patricia.supprimer(arbrePatricia, mot);
                long fin = System.nanoTime();
                tempsTotal += fin - start;
                mot = reader.readLine();
            }
            reader.close();
            System.out.println("Temps suppression : " + tempsTotal + "nanosecondes");

            // écriture dans le fichier pat.json

            File fichierJsonPatricia = new File("pat.json");
            if (fichierJsonPatricia.exists()) {
                fichierJsonPatricia.delete();
                // fichierJsonPatricia.createNewFile();
            } else {
                fichierJsonPatricia.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierJsonPatricia));
            writer.write(arbrePatricia.toString());
            writer.close();
            System.out.println("Arbre sauvegardé dans le fichier " +
                    fichierJsonPatricia.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
