#!/bin/bash

# Vérifie si deux arguments sont fournis
if [ "$#" -ne 3 ]; then 
    echo "Renseignez trois arguments";
    exit 1;
fi 

numero=$1
arbre1=$2
arbre2=$3

# vérifie si le premier argument est 0 ou 1

if [ "$numero" -eq 0 ]; then
    echo "Fusion des arbres patricia $arbre1 et $arbre2";
    java -classpath classes:lib/gson-2.11.0.jar Main fusion  "$arbre1" "$arbre2"
fi