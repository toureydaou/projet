package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.commons.annotation.Testable;

import patricia.Patricia;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPatricia {

    public static Patricia patriciaTest = new Patricia("", "");

    @BeforeEach
    void init() {
        patriciaTest = new Patricia("", "");
    }

    @Test
    void insertion() {
        Patricia.insertWord(patriciaTest, "car");
        Patricia.insertWord(patriciaTest, "cat");
        Patricia.insertWord(patriciaTest, "cart");
        Patricia.insertWord(patriciaTest, "dog");
        Patricia.insertWord(patriciaTest, "bat");
        try {
            assertEquals(new String(Files.readAllBytes(Paths.get("testFiles/insertion.json"))),
                    patriciaTest.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void comptageMots() {
        Patricia.insertWord(patriciaTest, "car");
        Patricia.insertWord(patriciaTest, "cat");
        Patricia.insertWord(patriciaTest, "cart");
        Patricia.insertWord(patriciaTest, "dog");
        Patricia.insertWord(patriciaTest, "bat");
        assertEquals(5, Patricia.comptageMots(patriciaTest));
    }

    @Test
    void listeMots() {
        Patricia.insertWord(patriciaTest, "car");
        Patricia.insertWord(patriciaTest, "cat");
        Patricia.insertWord(patriciaTest, "cart");
        Patricia.insertWord(patriciaTest, "dog");
        Patricia.insertWord(patriciaTest, "bat");
        String[] mots = { "bat", "car", "cart", "cat", "dog" };
        assertArrayEquals(mots, Patricia.listeMots(patriciaTest).toArray());
    }

    @Test
    void comptageNil() {
        Patricia.insertWord(patriciaTest, "car");
        Patricia.insertWord(patriciaTest, "cat");
        Patricia.insertWord(patriciaTest, "cart");
        Patricia.insertWord(patriciaTest, "dog");
        Patricia.insertWord(patriciaTest, "bat");
        assertEquals(375, Patricia.comptageNil(patriciaTest));
    }

    @Test
    void hauteur() {
        Patricia.insertWord(patriciaTest, "car");
        Patricia.insertWord(patriciaTest, "cat");
        Patricia.insertWord(patriciaTest, "cart");
        Patricia.insertWord(patriciaTest, "dog");
        Patricia.insertWord(patriciaTest, "bat");
        assertEquals(3, Patricia.hauteur(patriciaTest));
    }

    @Test
    void profondeurMoyenne() {
        Patricia.insertWord(patriciaTest, "car");
        Patricia.insertWord(patriciaTest, "cat");
        Patricia.insertWord(patriciaTest, "cart");
        Patricia.insertWord(patriciaTest, "dog");
        Patricia.insertWord(patriciaTest, "bat");
        assertEquals(1.75, Patricia.profondeurMoyenne(patriciaTest));
    }

    @Test
    void prefixe() {
        Patricia.insertWord(patriciaTest, "car");
        Patricia.insertWord(patriciaTest, "cat");
        Patricia.insertWord(patriciaTest, "cart");
        Patricia.insertWord(patriciaTest, "dog");
        Patricia.insertWord(patriciaTest, "bat");
        assertEquals(3, Patricia.prefixe(patriciaTest, "ca"));
    }

    @Test
    void suppression() {
        Patricia.insertWord(patriciaTest, "car");
        Patricia.insertWord(patriciaTest, "cat");
        Patricia.insertWord(patriciaTest, "cart");
        Patricia.insertWord(patriciaTest, "dog");
        Patricia.insertWord(patriciaTest, "bat");
        try {
            Patricia.supprimer(patriciaTest, "car");
            assertEquals(new String(Files.readAllBytes(Paths.get("testFiles/suppression.json"))),
                    patriciaTest.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void recherche() {
        Patricia.insertWord(patriciaTest, "car");
        Patricia.insertWord(patriciaTest, "cat");
        Patricia.insertWord(patriciaTest, "cart");
        Patricia.insertWord(patriciaTest, "dog");
        Patricia.insertWord(patriciaTest, "bat");

        assertTrue(Patricia.recherche(patriciaTest, "car"));
        assertFalse(Patricia.recherche(patriciaTest, "ca"));
    }
}
