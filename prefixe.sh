#!/bin/bash


# Vérifie si deux arguments sont fournis
if [ "$#" -ne 3 ]; then 
    echo "Renseignez trois arguments";
    exit 1;
fi 

numero=$1
arbre=$2
prefixe=$3


# vérifie si le premier argument est 0 ou 1

if [ "$numero" -eq 0 ]; then
    echo "Calcul du nombre de mot dont $prefixe est préfixe dans l'arbre $arbre";
    java -classpath classes:lib/gson-2.11.0.jar Main prefixe  "$arbre" "$prefixe"
elif [ "$numero" -eq 1 ]; then
    echo "Calcul du nombre de mot dont $prefixe est préfixe dans l'arbre $arbre";
    java -classpath classes:lib/gson-2.11.0.jar:lib/json-20211205.jar MainTrie prefixe  "$arbre" "$prefixe"
else
    echo " Error : x doit étre 0 ou 1"
    exit 1
fi