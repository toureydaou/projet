package patricia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FusionDossierPatricia {
    public static void fusion(String dossier) {
        try {

            ArrayList<Patricia> patriciaList = new ArrayList<>();

            File fileOrDirectory = new File(dossier);
            Patricia racine = new Patricia("", "");
            String mot;
            for (File fichier : fileOrDirectory.listFiles()) {
                BufferedReader reader = new BufferedReader(new FileReader(fichier));
                mot = reader.readLine();
                while (mot != null) {
                    Patricia.insertWord(racine, mot);
                    patriciaList.add(racine);
                    racine = new Patricia("", "");
                    mot = reader.readLine();

                }
                reader.close();
            }

            // instanciation des arbres patrica via les fichiers json

            long tempsTotal = 0;
            Patricia arbreFusion = new Patricia("", "");
            for (int i = 0; i < patriciaList.size(); i++) {
                long start = System.nanoTime();
                arbreFusion = Patricia.fusion(arbreFusion, patriciaList.get(i));
                long fin = System.nanoTime();
                tempsTotal += fin - start;
            }

            System.out.println("Temps d'execution de la fusion des arbre " + tempsTotal + " nanosecondes");

            // écriture dans le fichier pat.json

            File fichierJsonPatricia = new File("pat.json");
            if (fichierJsonPatricia.exists()) {
                fichierJsonPatricia.delete();
                // fichierJsonPatricia.createNewFile();
            } else {
                fichierJsonPatricia.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichierJsonPatricia));
            writer.write(arbreFusion.toString());
            writer.close();
            System.out.println("Arbre sauvegardé dans le fichier " +
                    fichierJsonPatricia.getPath());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
