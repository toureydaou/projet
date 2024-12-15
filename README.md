Language utilisé pour le projet : **JAVA**
Version utilisée : **17.0.13**

# Compilation

Pour pouvoir compiler le projet il suffit de lancer la commande :
`make compile `

# Exécution

- Commande insérant des mots dans un arbre patricia depuis un fichier (Linux)

  `./inserer.sh 0 cheminFichierMots.txt`

- Commande permettant de supprimer mots dans un arbre patricia depuis un fichier (Linux)

  `./suppression.sh 0 cheminFichierMots.txt`

- Commande permettant de lister les mots d'un arbre patricia (Linux)

  `./listeMots.sh 0 arbre.json`

- Commande permettant de calculer la profondeur moyenne d'un arbre patricia (Linux)

  `./profondeurMoyenne.sh 0 arbre.json`

- Commande permettant de calculer la hauteur d'un arbre patricia (Linux)

  `./hauteur.sh 0 arbre.json`

- Commande permettant de compter les mots d'un arbre patricia (Linux)

  `./comptageMots.sh 0 arbre.json`

- Commande permettant de compter le nombre de pointeurs vers Nil d'un arbre patricia (Linux)

  `./comptageNil.sh 0 arbre.json`

- Commande de compter le nombre de mots ayant pour préfixe la chaîne spécifiée (Linux)

  `./prefixe.sh 0 arbre.json prefixe`
