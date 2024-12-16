#!/bin/bash

# Vérifie si deux arguments sont fournis
if [ "$#" -ne 2 ]; then 
    echo "Renseignez deux arguments";
    exit 1;
fi 

numero=$1
dossier=$2
arbre2=$3

# vérifie si le premier argument est 0 ou 1

if [ "$numero" -eq 0 ]; then
    echo "Fusion des arbres patricia du dossier "$dossier"";
    java -classpath classes:lib/gson-2.11.0.jar Main fusionDossier  "$dossier"
fi