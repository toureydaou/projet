package patricia;
import java.lang.reflect.Type;
import java.util.TreeMap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class PatricaJsonDeserializer implements JsonDeserializer<Patricia> {

    @Override
    public Patricia deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Patricia patricia = new Patricia();

        if (jsonObject.has("label")) {
            patricia.setPrefixe(jsonObject.get("label").getAsString());
        }

        if (jsonObject.has("is_end_of_word")) {
            boolean finDemot = jsonObject.get("is_end_of_word").getAsBoolean();
            if (finDemot) {
                patricia.setFinDeMot("@");
            } else {
                patricia.setFinDeMot("");
            }

        }

        if (jsonObject.has("children")) {
            TreeMap<String, Patricia> sousArbre = context.deserialize(
                    jsonObject.get("children"),
                    new TypeToken<TreeMap<String, Patricia>>() {
                    }.getType());
            patricia.setNoeud(sousArbre);
        }

        return patricia;
    }

}
