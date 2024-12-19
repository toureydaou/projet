#!/bin/bash

# Vérifie si deux arguments sont fournis
if [ "$#" -ne 2 ]; then 
    echo "Renseignez deux arguments";
    exit 1;
fi 

x=$1
y=$2

# vérifie si le premier argument est 0 ou 1

if [ "$x" -eq 0 ]; then
    echo "Hauteur de l'arbre patricia $y";
    java -classpath classes:lib/gson-2.11.0.jar Main hauteur  "$y"
elif [ " $x " -eq 1 ]; then
    echo "Hauteur du trie hybride $y";
    java -classpath classes:lib/gson-2.11.0.jar:lib/json-20211205.jar MainTrie hauteur  "$y"
else
    echo " Error : x doit étre 0 ou 1"
    exit 1
fi