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
fi