package patricia;

import java.util.ArrayList;
import java.util.TreeMap;
import com.google.gson.annotations.SerializedName;

public class Patricia {
    @SerializedName("children")
    public TreeMap<String, Patricia> noeud = new TreeMap<String, Patricia>();
    @SerializedName("label")
    public String prefixe;
    @SerializedName("is_end_of_word")
    public String finDeMot = "";

    public static int complexRecherche = 0;
    public static int complexComptageMots = 0;
    public static int complexeComptageNil = 0;
    public static int complexeHauteur = 0;
    public static int complexeProfondeurMoyenne = 0;
    public static int complexePerfixe = 0;
    public static int complexeSuppresion = 0;
    public static int complexeAppel = 0;

    public Patricia() {

    }

    public Patricia(String prefixe, String finDeMot) {
        this.prefixe = prefixe;
        this.finDeMot = finDeMot;
    }

    public Patricia(TreeMap<String, Patricia> noeud, String prefixe, String finDeMot) {
        this.noeud = noeud;
        this.prefixe = prefixe;
        this.finDeMot = finDeMot;
    }

    public boolean isEndOfWord() {
        return finDeMot == "@" ? true : false;
    }

    public TreeMap<String, Patricia> getNoeud() {
        return noeud;
    }

    public void setNoeud(TreeMap<String, Patricia> noeud) {
        this.noeud = noeud;
    }

    public String getPrefixe() {
        return prefixe;
    }

    public void setPrefixe(String prefixe) {
        this.prefixe = prefixe;
    }

    public String getFinDeMot() {
        return finDeMot;
    }

    public void setFinDeMot(String finDeMot) {
        this.finDeMot = finDeMot;
    }

    @Override
    public String toString() {
        String string = "{\n" +
                "\"label\": \"" + prefixe + "\",\n" +
                "\"is_end_of_word\": " + (this.isEndOfWord()) + ",\n" +
                "\"children\": {\n";

        if (noeud != null && !noeud.isEmpty()) {

            for (String key : noeud.keySet()) {
                string += "      \"" + key + "\": " + noeud.get(key).toString() + "\n" +
                        "    ,\n";
            }

            if (string.endsWith(",\n")) {
                string = string.substring(0, string.length() - 2) + "\n";
            }
        }

        string += "  }\n}";
        return string;
    }

    // permet de rechercher le préfixe associé à la clé dans un noeud patricia
    public String getPrefixeByKey(String cle) {

        // si la clée est retrouvée on retourne le préfixe associé
        if (this.noeud.containsKey(cle)) {
            return this.noeud.get(cle).getPrefixe();
        }
        // si la clé n'existe pas on retourne un valeur nulle
        return null;
    }

    // fonction permettant de récupérer le prefixe commun entre deux mots
    public static String getCommonPrefixe(String prefixe, String mot) {
        /*
         * on suppose que le prefixe commun aura pour longueur maximale la longueur du
         * mot le plus court
         */
        int longeurMax = prefixe.length() < mot.length() ? prefixe.length() : mot.length();

        // on initialise le préfixe commun à null
        String commonPrefixe = null;
        for (int i = 0; i < longeurMax + 1; i++) {
            if (prefixe.substring(0, i).equals(mot.substring(0, i))) {
                commonPrefixe = mot.substring(0, i);
            } else {
                return commonPrefixe;
            }
        }
        return commonPrefixe;
    }

    // fonction permettant d'inserer un mot dans un patricia tree
    public static Patricia insertWord(Patricia patrica, String mot) {
        // on récupère la première lettre du mot

        String debutMot = "";
        debutMot = mot.substring(0, 1);

        String prefixe = patrica.getPrefixeByKey(debutMot);

        /*
         * si le préfixe est null celà implique la clée n'existe pas dans le noeud
         * donc on insère un la clé dans le noeud avec comme préfixe le mot à insérer
         * et celui-ci est défini comme une fin de mot
         */
        if (prefixe == null) {
            Patricia sousArbrePatricia = new Patricia(mot, "@");
            patrica.getNoeud().put(debutMot, sousArbrePatricia);
            return patrica;
        }

        /*
         * si le préfixe est contenu dans le mot à insérer alors on insére le
         * reste du mot dans le noeud fils associé au préfixe
         */
        if (mot.startsWith(prefixe)) {
            Patricia noeudFils = patrica.getNoeud().get(debutMot);

            if (mot.length() > prefixe.length()) {
                Patricia.insertWord(noeudFils, mot.substring(prefixe.length()));
            } else {

                noeudFils.setFinDeMot("@");
            }
            return patrica;
        }

        /*
         * dans le dernier cas le mot et préfixe partagent un préfixe commun
         * mais le préfixe n'est pas contenu totalement dans le mot
         */

        String nouveauPrefixe = Patricia.getCommonPrefixe(prefixe, mot);
        Patricia nouveauNoeud = new Patricia(nouveauPrefixe, "");
        Patricia ancienSousArbre = patrica.getNoeud().get(debutMot);
        ancienSousArbre.setPrefixe(prefixe.substring(nouveauPrefixe.length()));
        nouveauNoeud.getNoeud().put(
                prefixe.substring(nouveauPrefixe.length(), nouveauPrefixe.length() + 1), ancienSousArbre);
        String resteMot = mot.substring(nouveauPrefixe.length());
        if (!resteMot.isEmpty()) {
            Patricia nouveauSousArbre = new Patricia(resteMot, "@");
            nouveauNoeud.getNoeud().put(resteMot.substring(0, 1), nouveauSousArbre);
        } else {
            nouveauNoeud.setFinDeMot("@");
        }

        patrica.getNoeud().put(debutMot, nouveauNoeud);
        return patrica;
    }

    // fonction recherchant un mot dans un arbre patricia
    public static boolean recherche(Patricia arbre, String mot) {
        if (mot.isEmpty()) {
            return false;
        }
        String debut_mot = mot.substring(0, 1);

        /*
         * on vérifie d'abord si la premiere lettre du mot à un préfixe qui lui est
         * associé
         */
        //
        if (arbre.getPrefixeByKey(debut_mot) != null) {
            complexRecherche++;
            String prefixe = arbre.getPrefixeByKey(debut_mot);
            // ici on vérifie si le préfixe est préfixe de notre mot
            if (mot.startsWith(prefixe) && mot.equals(prefixe)) {
                if (arbre.getNoeud().get(debut_mot).isEndOfWord()) {
                    System.out.println(complexRecherche);
                    return true;
                } else {
                    return false;
                }

            }

            /*
             * si le prefixe n'est pas égal au mot alors on recherche dans le noeud
             * fils du préfixe avec le reste du mot ayant le préfixe soustrait
             */

            if (mot.length() > prefixe.length()) {
                return Patricia.recherche(arbre.getNoeud().get(debut_mot), mot.substring(prefixe.length()));
            }
        }
        System.out.println(complexRecherche);
        return false;

    }

    // nombre de mot contenus dans l'arbre patricia

    public static int comptageMots(Patricia arbre) {
        /*
         * si l'arbre est vide alors on a pas de mot et on renvoie 0
         * la structure de nos arbre suppose une racine ne contenant aucun mot
         * donc si le sous noeud de la racine ne contient aucun préfixe alors
         * l'arbre est considéré comme vide
         */
        if (arbre.getNoeud().size() == 0) {

            return 0;
        }

        /*
         * si l'arbre n'est pas vide on parcours les noeuds fils
         */
        int nbreMots = 0;
        for (Patricia patricia : arbre.getNoeud().values()) {
            /*
             * si on rencontre une fin de noeud on incrémente le nombre
             * de noeuds de 1
             */
            complexComptageMots++;
            if (patricia.isEndOfWord()) {
                nbreMots++;
            }
            /*
             * si le noeud est une fin de mot ou non on continue
             */
            nbreMots += Patricia.comptageMots(patricia);
        }
        System.out.println("complexComptageMots : " + complexComptageMots);
        return nbreMots;

    }

    // fonction permettant de retourner la liste de mots de l'arbre patricia
    public static ArrayList<String> listeMots(Patricia arbre) {
        ArrayList<String> mots = new ArrayList<>();
        if (arbre.getNoeud().size() == 0) {
            return mots;
        }

        /*
         * appel de la fonction concatenant les préfixes avec un mot accumulateur
         * initialisé vide le tableau de mots que l'on rempli au fur et à mesure
         */
        Patricia.concatenePrefixe(arbre, "", mots);
        return mots;
    }

    /*
     * fonction permettant de former les mots en contenant les prefixes des noeuds
     * visités
     */
    public static void concatenePrefixe(Patricia arbre, String motAccumulateur, ArrayList<String> mots) {

        /*
         * on crée le nouveau mot en concaténant les préfixe accumulés avec le
         * préfixe du noeud courant
         */

        String nouveauMot = motAccumulateur + arbre.getPrefixe();

        /*
         * si le préfixe est une fin de mot on rajoute le mot
         * à la liste des mots de l'arbre patricia
         */
        if (arbre.isEndOfWord()) {
            mots.add(nouveauMot);
        }

        /*
         * on parcours les sous noeuds tout en appelant de façon récursive la fonction
         */
        for (Patricia enfant : arbre.getNoeud().values()) {
            Patricia.concatenePrefixe(enfant, nouveauMot, mots);
        }

    }

    // fonction permettant de compter les pointeurs nuls dans l'arbre
    public static int comptageNil(Patricia arbre) {
        /*
         * si la racine ne contient pas d'enfant donc il y'a 127 pointeurs vides
         * (un arbre vide à tous ses pointeurs à Nil)
         */

        if (arbre.getPrefixe().equals("") && arbre.getNoeud().size() == 0) {
            complexeComptageNil++;
            return 127;
        }

        // dans le cas où la racine n'est pas vide
        int nbreNil = 0;
        if (arbre.getNoeud().size() == 0) {
            complexeComptageNil++;
            return 0;
        } else {
            // le nombre de pointeurs vides est égal à 127 moins le nombre de clés
            // dans le noeud
            complexeComptageNil++;
            nbreNil += 127 - arbre.getNoeud().size();
        }

        for (Patricia patricia : arbre.getNoeud().values()) {
            nbreNil += Patricia.comptageNil(patricia);

        }
        System.out.println("complexeComptageNil : " + complexeComptageNil);
        return nbreNil;
    }

    /*
     * fonction auxilliare permettant de calculer la profondeur de chaque feuille de
     * l'arbre
     */
    public static int profondeurNoeud(Patricia arbre, int profondeurCourante) {

        // si on atteint une feuille on retourne la profondeur trouvée
        complexeHauteur++;
        if (arbre.getNoeud().size() == 0) {
            return profondeurCourante;
        }
        // dans le cas contraire on met à jour la profondeur et on continue
        // par descendre dans les noeuds
        int hauteur = profondeurCourante;

        for (Patricia patricia : arbre.getNoeud().values()) {
            int profondeurNoeudCourant = Patricia.profondeurNoeud(patricia, profondeurCourante + 1);
            // si on récupère une profondeur d'une feuille on la compare
            // avec la hauteur précédent récupérée le maximun correspond
            // à la hauteur de l'arbre
            complexeHauteur++;
            hauteur = Math.max(profondeurNoeudCourant, hauteur);

        }
        System.out.println("complexeHauteur " + complexeHauteur);
        return hauteur;
    }

    // fonction calculant la hauteur d'un arbre patricia
    public static int hauteur(Patricia arbre) {
        return Patricia.profondeurNoeud(arbre, 0);
    }

    // fonction permettant de récupérer la profondeur des feuilles de l'arbre
    public static void profondeurNoeuds(Patricia arbre, int profondeurCourante, ArrayList<Integer> profondeurs) {

        // si on atteint une feuille on retourne la profondeur trouvée
        if (arbre.getNoeud().size() == 0) {
            complexeProfondeurMoyenne++;
            profondeurs.add(profondeurCourante);
            return;
        }
        for (Patricia patricia : arbre.getNoeud().values()) {
            Patricia.profondeurNoeuds(patricia, profondeurCourante + 1, profondeurs);
        }

    }

    // fonction calculant la profondeur moyenne des feuilles de l'arbre
    public static double profondeurMoyenne(Patricia arbre) {
        ArrayList<Integer> profondeurs = new ArrayList<>();
        Patricia.profondeurNoeuds(arbre, 0, profondeurs);
        int sommeProfondeurs = 0;
        for (Integer profondeur : profondeurs) {
            sommeProfondeurs += profondeur;
        }
        System.out.println("complexeProfondeurMoyenne " + complexeProfondeurMoyenne);
        return (double) sommeProfondeurs / (double) profondeurs.size();
    }

    /*
     * fonction permettant de compter le nombre de mot dont la chaîne de caractère
     * est préfixe dans l'arbre
     */
    public static int prefixe(Patricia arbre, String prefixe) {
        // si l'arbre est vide alors la chaîne n'est préfixe d'aucun mot
        if (arbre.getNoeud().size() == 0) {
            return 0;
        }

        if (arbre.getNoeud().get(prefixe.substring(0, 1)) != null) {
            String prefixeArbre = arbre.getPrefixeByKey(prefixe.substring(0, 1));

            complexePerfixe++;
            if (prefixeArbre.length() >= prefixe.length() && prefixeArbre.startsWith(prefixe)) {

                Patricia patricia = new Patricia();

                patricia.getNoeud().put(prefixe.substring(0, 1), arbre.getNoeud().get(prefixe.substring(0, 1)));
                // compte maintenant le nombre de mots dans l'arbre
                System.out.println("complexePerfixe : " + complexePerfixe);
                return Patricia.comptageMots(patricia);
            }

            if (prefixeArbre.length() < prefixe.length() && prefixe.startsWith(prefixeArbre)) {

                return Patricia.prefixe(arbre.getNoeud().get(prefixeArbre.substring(0, 1)),
                        prefixe.substring(prefixeArbre.length()));
            }
        }
        // si la première lettre du prefixe ne contient aucune valeur on retourne 0
        return 0;
    }

    /*
     * fonction permettant de supprimer un mot dans un arbre
     */
    public static Patricia supprimer(Patricia arbre, String mot) {

        if (!Patricia.recherche(arbre, mot) || mot.isEmpty()) {
            return arbre;
        }

        String debut_mot = mot.substring(0, 1);

        Patricia sousPatricia = arbre.getNoeud().get(debut_mot);

        if (mot.equals(arbre.getNoeud().get(debut_mot).getPrefixe()) && arbre.getNoeud().get(debut_mot).isEndOfWord()) {

            sousPatricia.setFinDeMot("");

            if (sousPatricia.getNoeud().isEmpty()) {
                arbre.getNoeud().remove(debut_mot);
            }

            if (sousPatricia.getNoeud().size() == 1) {
                Patricia sousSousArbre = sousPatricia.getNoeud().values().iterator().next();
                String prefixeFusion = sousPatricia.getPrefixe() + sousSousArbre.getPrefixe();
                sousSousArbre.setPrefixe(prefixeFusion);
                arbre.getNoeud().replace(debut_mot, sousSousArbre);
            }

            return arbre;
        }

        complexeSuppresion++;
        if (arbre.getNoeud().containsKey(debut_mot)) {

            Patricia.supprimer(sousPatricia, mot.substring(sousPatricia.getPrefixe().length()));

            if (!sousPatricia.isEndOfWord() && sousPatricia.getNoeud().size() == 1) {
                Patricia sousSousArbre = sousPatricia.getNoeud().values().iterator().next();
                String prefixeFusion = sousPatricia.getPrefixe() + sousSousArbre.getPrefixe();
                sousSousArbre.setPrefixe(prefixeFusion);
                arbre.getNoeud().replace(debut_mot, sousSousArbre);
            }
        }

        System.out.println("complexeSuppresion " + complexeSuppresion);
        return arbre;
    }

    // fonction permettant de fusionner deux arbre patricia
    public static Patricia fusion(Patricia patricia_1, Patricia patricia_2) {

        if (patricia_1.getNoeud().size() == 0) {
            return patricia_2;
        }

        if (patricia_2.getNoeud().size() == 0) {
            return patricia_1;
        }

        // on parcourt toute les clées du deuxième arbre
        for (String cle : patricia_2.getNoeud().keySet()) {

            // si la clée n'est pas présente dans l'arbre 1
            if (!patricia_1.getNoeud().containsKey(cle)) {
                patricia_1.getNoeud().put(cle, patricia_2.getNoeud().get(cle));
            }

            // si la cle est contenue dans le premier arbre
            if (patricia_1.getNoeud().containsKey(cle)) {
                // on compare alors les préfixes
                String prefixeArbre1 = patricia_1.getNoeud().get(cle).getPrefixe();
                String prefixeArbre2 = patricia_2.getNoeud().get(cle).getPrefixe();

                // si les deux on le même préfixe
                if (prefixeArbre1.equals(prefixeArbre2)) {
                    patricia_1.getNoeud().put(cle,
                            Patricia.fusion(patricia_1.getNoeud().get(cle),
                                    patricia_2.getNoeud().get(cle)));
                }

                // s'ils ont un préfixe commun
                String prefixeCommun = getCommonPrefixe(prefixeArbre1, prefixeArbre2);
                if (prefixeCommun.length() != prefixeArbre1.length()
                        || prefixeCommun.length() != prefixeArbre2.length()) {

                    String findeMot1 = patricia_1.getNoeud().get(cle).isEndOfWord() ? "@" : "";
                    String findeMot2 = patricia_2.getNoeud().get(cle).isEndOfWord() ? "@" : "";
                    Patricia sousArbre1 = new Patricia(patricia_1.getNoeud().get(cle).getNoeud(),
                            prefixeArbre1.substring(prefixeCommun.length()), findeMot1);

                    Patricia sousArbre2 = new Patricia(patricia_2.getNoeud().get(cle).getNoeud(),
                            prefixeArbre2.substring(prefixeCommun.length()), findeMot2);

                    Patricia fusionSousArbre = Patricia.fusion(sousArbre1, sousArbre2);

                    Patricia noeudCommun = new Patricia(prefixeCommun, "");
                    noeudCommun.getNoeud().put(prefixeCommun.substring(0, 1), fusionSousArbre);

                    patricia_1.getNoeud().put(cle, noeudCommun);
                }
            }
        }

        return patricia_1;
    }
}