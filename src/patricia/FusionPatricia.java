package patricia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FusionPatricia {
    public static void fusion(String file1, String file2) {
        try {
            // instanciation des arbres patrica via les fichiers json
            Gson gson = new GsonBuilder().registerTypeAdapter(Patricia.class, new PatricaJsonDeserializer()).create();
            String patriciaJson1;
            patriciaJson1 = new String(Files.readAllBytes(Paths.get(file1)));
            Patricia arbrePatricia1 = gson.fromJson(patriciaJson1, Patricia.class);

            String patriciaJson2;
            patriciaJson2 = new String(Files.readAllBytes(Paths.get(file2)));
            Patricia arbrePatricia2 = gson.fromJson(patriciaJson2, Patricia.class);

            Patricia arbreFusion = Patricia.fusion(arbrePatricia1, arbrePatricia2);

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
