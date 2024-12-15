# localisation des dossiers sources et classes
SOURCE_DIR := src
OUTPUT_DIR := classes 

compile:
	javac -sourcepath $(SOURCE_DIR) $(SOURCE_DIR)/Main.java -cp lib/gson-2.11.0.jar -d $(OUTPUT_DIR) 

clean:
	rm -r $(OUTPUT_DIR)