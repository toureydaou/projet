#!/bin/bash


# Vérifie si deux arguments sont fournis
if [ "$#" -ne 3 ]; then 
    echo "Renseignez trois arguments";
    exit 1;
fi 

numero=$1
arbre=$2
mot=$3


# vérifie si le premier argument est 0 ou 1

if [ "$numero" -eq 0 ]; then
    echo "Recherche du mot $mot dans l'arbre $arbre";
    java -classpath classes:lib/gson-2.11.0.jar Main recherche "$arbre" "$mot"
elif [ " $numero " -eq 1 ]; then
    echo "Recherche du mot $mot dans le trie hybride $arbre";
    java -classpath classes:lib/gson-2.11.0.jar:lib/json-20211205.jar MainTrie recherche  "$arbre" "$mot"
else
    echo " Error : x doit étre 0 ou 1"
    exit 1
fi