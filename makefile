# localisation des dossiers sources et classes
SOURCE_DIR := src
OUTPUT_DIR := classes 

# commande permettant de compiler le projet
compile:
	javac -sourcepath $(SOURCE_DIR) $(SOURCE_DIR)/Main.java -cp lib/gson-2.11.0.jar -d $(OUTPUT_DIR) 

# commande permettant de supprimer les .class
clean:
	rm -r $(OUTPUT_DIR)