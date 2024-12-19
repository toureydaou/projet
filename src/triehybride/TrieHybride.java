package triehybride;

import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrieHybride {

    protected Noeud racine;
    protected int compteur;
    protected HashMap<String, Integer> table;

    public TrieHybride() {
        this.racine = null;
        this.table = new HashMap<>();
        this.compteur = 0;
    }

    public char prem(String cle) {
        return cle.charAt(0);
    }

    public String reste(String cle) {
        return cle.substring(1, cle.length());
    }

    public char car(String cle, int i) {
        return cle.charAt(i);
    }

    public int longueur(String cle) {
        return cle.length();
    }

    public Noeud NoeudVide() {
        return new Noeud(' ', null);
    }

    public Boolean EstVide(Noeud A) {
        return A == null;
    }

    public Noeud getRacine() {
        return this.racine;
    }

    public void importJson(String nomFichierJson) {
        try {

            String jsonStr = new String(Files.readAllBytes(Paths.get(nomFichierJson)));

            if (jsonStr == null || jsonStr.trim().isEmpty() || jsonStr.equals("null")) {
                this.racine = null;
                return;
            }
            try {
                JSONObject obj = new JSONObject(jsonStr);
                this.racine = fromJsonObject(obj);
            } catch (JSONException e) {
                e.printStackTrace();
                this.racine = null;
            }

            System.out.println("Arbre reconstruit depuis le fichier JSON !");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Noeud fromJsonObject(JSONObject obj) throws JSONException {
        if (obj == null || obj.equals(JSONObject.NULL)) {
            return null;
        }

        char val = prem(obj.getString("char"));

        boolean isEndOfWord = obj.getBoolean("is_end_of_word");
        int finMot = isEndOfWord ? ++compteur : 0;

        Noeud Inf = null;
        if (!obj.isNull("left")) {
            JSONObject left = obj.optJSONObject("left");
            if (left != null) {
                Inf = fromJsonObject(left);
            }
        }

        Noeud Eq = null;
        if (!obj.isNull("middle")) {
            JSONObject middle = obj.optJSONObject("middle");
            if (middle != null) {
                Eq = fromJsonObject(middle);
            }
        }

        Noeud Sup = null;
        if (!obj.isNull("right")) {
            JSONObject right = obj.optJSONObject("right");
            if (right != null) {
                Sup = fromJsonObject(right);
            }
        }

        return new Noeud(val, Inf, Eq, Sup, finMot);
    }

    public void reequilibrage(Noeud A) {
        int facteur = this.TauxDesequilibre(A);
        int seuil = 5;
        if (facteur > seuil) {
            if (A.GetInf() != null) {
                if (TauxDesequilibre(A.GetInf()) > seuil) {
                    rotationGauche(A.GetInf());
                } else if (TauxDesequilibre(A.GetInf()) < -seuil) {
                    rotationDroite(A.GetInf());
                }
            }
            rotationDroite(A);
        }

        if (facteur < -seuil) {
            if (A.GetSup() != null) {
                if (TauxDesequilibre(A.GetSup()) > seuil) {
                    rotationDroite(A.GetSup()); // Rotation gauche sur sous-arbre gauche
                } else if (TauxDesequilibre(A.GetSup()) < -seuil) {
                    rotationGauche(A.GetSup());
                }
            }
            rotationGauche(A);
        }
    }

    public int TauxDesequilibre(Noeud A) {
        int infValue = A.GetInf() == null ? 0 : this.ComptageMots(A.GetInf());
        int supValue = A.GetSup() == null ? 0 : this.ComptageMots(A.GetSup());
        return infValue - supValue;
    }

    public void rotationGauche(Noeud A) {
        if (A.GetSup() == null) {
            A.SetSup(this.NoeudVide());
        }
        Noeud y = new Noeud(A.Val(), A.GetInf(), A.GetEq(), A.GetSup().GetInf(), A.GetFinMot());
        Noeud rt = new Noeud(A.GetSup().Val(), y, A.GetSup().GetEq(), A.GetSup().GetSup(), A.GetSup().GetFinMot());
        A.SetVal(rt.Val());
        A.SetFinMot(rt.GetFinMot());
        A.SetEq(rt.GetEq());
        A.SetSup(rt.GetSup());
        A.SetInf(rt.GetInf());
    }

    public void rotationDroite(Noeud A) {
        if (A.GetInf() == null) {
            A.SetInf(this.NoeudVide());
        }
        Noeud y = new Noeud(A.Val(), A.GetInf().GetSup(), A.GetEq(), A.GetSup(), A.GetFinMot());
        Noeud rt = new Noeud(A.GetInf().Val(), A.GetInf().GetInf(), A.GetInf().GetEq(), y, A.GetInf().GetFinMot());
        A.SetVal(rt.Val());
        A.SetFinMot(rt.GetFinMot());
        A.SetEq(rt.GetEq());
        A.SetSup(rt.GetSup());
        A.SetInf(rt.GetInf());
    }

    public boolean Recherche(Noeud A, String mot) {
        if (A == null || mot == null || longueur(mot) == 0) {
            return false;
        }

        char p = prem(mot);

        if (p < A.Val()) {
            return Recherche(A.GetInf(), mot);
        } else if (p > A.Val()) {
            return Recherche(A.GetSup(), mot);
        }

        else {
            if (longueur(mot) == 1) {
                return A.GetFinMot() > 0;
            } else {
                return Recherche(A.GetEq(), reste(mot));
            }
        }
    }

    public void inserer(String c) {
        if (!table.containsKey(c)) {
            compteur++;
            table.put(c, compteur);
            this.racine = this.ajout(c, racine, compteur);
        }
    }

    public void insererReequilibrage(String c) {
        if (!table.containsKey(c)) {
            compteur++;
            table.put(c, compteur);
            this.racine = this.ajout(c, racine, compteur);
        }
        reequilibrage(this.racine);
    }

    public int ComptageMots(Noeud A) {
        if (A == null) {
            return 0;
        }

        int count = 0;

        if (A.GetFinMot() > 0) {
            count++;
        }

        count += ComptageMots(A.GetInf());
        count += ComptageMots(A.GetEq());
        count += ComptageMots(A.GetSup());

        return count;
    }

    public Noeud Suppression(Noeud A, String mot) {
        if (A == null || mot == null || longueur(mot) == 0) {
            return A;
        }

        char lettre = prem(mot);

        if (lettre < A.Val()) {
            A.SetInf(Suppression(A.GetInf(), mot));
        } else if (lettre > A.Val()) {
            A.SetSup(Suppression(A.GetSup(), mot));
        } else {
            A.SetEq(Suppression(A.GetEq(), reste(mot)));
            if (A.GetInf() == null && A.GetEq() == null && A.GetSup() == null) {
                A = null;
            } else if (A.GetEq() != null) {
                return A;
            } else if (A.GetInf() != null && A.GetSup() == null) {
                return A.GetInf();
            } else if (A.GetSup() != null && A.GetInf() == null) {
                return A.GetSup();
            } else if (A.GetSup() != null && A.GetInf() != null) {
                Noeud connectant = A.GetInf().GetSup();
                if (connectant != null) {
                    while (connectant.GetSup() != null) {
                        connectant = connectant.GetSup();
                    }
                    connectant.SetSup(A.GetSup());
                    A = A.GetInf();
                    return A;
                } else {
                    A.GetInf().SetSup(A.GetSup());
                    A = A.GetInf();
                    return A;
                }

            }
        }

        return A;
    }

    public Noeud ajout(String c, Noeud A, int v) {

        if (EstVide(A)) {
            if (longueur(c) == 1) {
                return new Noeud(prem(c), null, null, null, v);
            } else {
                A = new Noeud(' ', null, null, null, 0);
                return new Noeud(prem(c), null, ajout(reste(c), A.GetEq(), v), null, 0);
            }
        } else {
            char p = prem(c);
            if (p < A.Val()) {
                return new Noeud(A.Val(), ajout(c, A.GetInf(), v), A.GetEq(), A.GetSup(), A.GetFinMot());
            }
            if (p > A.Val()) {
                return new Noeud(A.Val(), A.GetInf(), A.GetEq(), ajout(c, A.GetSup(), v), A.GetFinMot());
            }
            if (longueur(c) == 1) {
                if (A.GetFinMot() > 0) {
                    return new Noeud(A.Val(), A.GetInf(), A.GetEq(), A.GetSup(), A.GetFinMot());
                } else {
                    return new Noeud(A.Val(), A.GetInf(), A.GetEq(), A.GetSup(), v);
                }
            }
            return new Noeud(A.Val(), A.GetInf(), ajout(reste(c), A.GetEq(), v), A.GetSup(), A.GetFinMot());
        }

    }

    public List<String> ListeMots(Noeud A) {
        return ParserDeMots(A, "");
    }

    private List<String> ParserDeMots(Noeud A, String mot) {
        List<String> liste = new ArrayList<>();
        if (A == null) {
            return liste;
        }

        liste.addAll(ParserDeMots(A.GetInf(), mot));
        String nouveauMot = mot + A.Val();

        if (A.GetFinMot() > 0) {
            liste.add(nouveauMot);
        }

        liste.addAll(ParserDeMots(A.GetEq(), nouveauMot));
        liste.addAll(ParserDeMots(A.GetSup(), mot));

        return liste;
    }

    public int ComptageNil(Noeud A) {
        if (A == null) {
            return 1;
        }

        int count = 0;

        if (A.GetInf() == null) {
            count += 1;
        } else {
            count += ComptageNil(A.GetInf());
        }
        if (A.GetEq() == null) {
            count += 1;
        } else {
            count += ComptageNil(A.GetEq());
        }
        if (A.GetSup() == null) {
            count += 1;
        } else {
            count += ComptageNil(A.GetSup());
        }

        return count;
    }

    public int Hauteur(Noeud A) {
        if (A == null) {
            return 0;
        }

        int hauteurInf = Hauteur(A.GetInf());
        int hauteurEq = Hauteur(A.GetEq());
        int hauteurSup = Hauteur(A.GetSup());

        return 1 + Math.max(hauteurInf, Math.max(hauteurEq, hauteurSup));
    }

    public int ProfondeurMoyenne(Noeud A) {

        int[] Pmoyenne = CalculProfondeur(A, 1);
        if (Pmoyenne[1] == 0) {
            return 0;
        }
        return Pmoyenne[0] / Pmoyenne[1];
    }

    // Pmoyenne[0] : la somme des profondeurs de feuilles
    // Pmoyenne[1] : le nombre de feuilles
    private int[] CalculProfondeur(Noeud A, int p) {
        int[] Pmoyenne = new int[2];

        if (A == null) {
            return Pmoyenne;
        }

        if (A.GetInf() == null && A.GetEq() == null && A.GetSup() == null) {
            Pmoyenne[0] = p;
            Pmoyenne[1] = 1;
            return Pmoyenne;
        }

        int[] PmoyenneInf = CalculProfondeur(A.GetInf(), p + 1);
        int[] PmoyenneEq = CalculProfondeur(A.GetEq(), p + 1);
        int[] PmoyenneSup = CalculProfondeur(A.GetSup(), p + 1);

        Pmoyenne[0] += PmoyenneInf[0] + PmoyenneEq[0] + PmoyenneSup[0];
        Pmoyenne[1] += PmoyenneInf[1] + PmoyenneEq[1] + PmoyenneSup[1];

        return Pmoyenne;
    }

    public int Prefixe(Noeud A, String mot) {
        Noeud noeud = trouverFinPrefixe(A, mot);

        if (noeud == null) {
            return 0;
        }

        int count = 0;
        if (noeud.GetFinMot() > 0) {
            count++;
        }

        count += ComptageMots(noeud.GetEq());
        return count;
    }

    private Noeud trouverFinPrefixe(Noeud A, String mot) {
        if (A == null || mot == null || mot.length() == 0) {
            return null;
        }
        char p = prem(mot);
        if (p < A.Val()) {
            return trouverFinPrefixe(A.GetInf(), mot);
        } else if (p > A.Val()) {
            return trouverFinPrefixe(A.GetSup(), mot);
        } else {
            if (longueur(mot) == 1) {
                return A;
            } else {
                return trouverFinPrefixe(A.GetEq(), reste(mot));
            }
        }
    }

    private String toJson(Noeud A, int p) {
        if (A == null) {
            return "null";
        }

        String json = "";
        json += "{\n";
        json += indent(p + 1) + "\"char\": \"" + A.Val() + "\",\n";
        json += indent(p + 1) + "\"is_end_of_word\": " + ((A.GetFinMot() > 0) ? "true" : "false") + ",\n";
        json += indent(p + 1) + "\"left\": " + toJson(A.GetInf(), p + 1) + ",\n";
        json += indent(p + 1) + "\"middle\": " + toJson(A.GetEq(), p + 1) + ",\n";
        json += indent(p + 1) + "\"right\": " + toJson(A.GetSup(), p + 1) + "\n";
        json += indent(p) + "}";

        return json;
    }

    private String indent(int p) {

        String json = "";
        for (int i = 0; i < p; i++) {
            json += "    ";
        }
        return json;
    }

    public void exportJson() {
        try (PrintWriter out = new PrintWriter("trie.json")) {
            out.println(this);
            System.out.println("Trie saved to trie.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return toJson(this.getRacine(), 0);
    }

    public static void main(String[] args) {

        TrieHybride trie = new TrieHybride();
        System.out.println("Nombre de mots insérés : " + trie.ComptageMots(trie.getRacine()));
        trie.insererReequilibrage("car");
        trie.insererReequilibrage("cat");
        trie.insererReequilibrage("camel");
        trie.insererReequilibrage("cash");
        System.out.println(trie);
    }
}
